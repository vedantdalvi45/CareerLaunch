package com.ved.CareerLaunch.service.impl;


import com.ved.CareerLaunch.dto.request.JobRequest;
import com.ved.CareerLaunch.model.Job;
import com.ved.CareerLaunch.model.User;
import com.ved.CareerLaunch.repository.JobRepository;
import com.ved.CareerLaunch.repository.UserRepository;
import com.ved.CareerLaunch.service.JobService;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
public class JobServiceImpl implements JobService {

    private final JobRepository jobRepository;
    private final UserRepository userRepository;

    // --- Helper method to get the currently authenticated user ---
    private User getAuthenticatedUser() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Authenticated user not found"));
    }

    // --- PUBLIC / APPLICANT METHODS ---

    @Override
    public List<Job> getAllPublicJobs() {
        return jobRepository.findAll();
    }

    @Override
    public Job getJobById(String jobId) {
        return jobRepository.findById(jobId)
                .orElseThrow(() -> new RuntimeException("Job not found"));
    }

    // --- RECRUITER METHODS ---

    @Override
    public Job postJob(JobRequest jobRequest) {
        User recruiter = getAuthenticatedUser();

        Job newJob = new Job();
        newJob.setRecruiterId(recruiter.getId());
        newJob.setTitle(jobRequest.getTitle());
        newJob.setDescription(jobRequest.getDescription());
        newJob.setCompanyName(jobRequest.getCompanyName());
        newJob.setLocation(jobRequest.getLocation());
        newJob.setPostedDate(Instant.now());

        return jobRepository.save(newJob);
    }

    @Override
    public List<Job> getMyPostedJobs() {
        User recruiter = getAuthenticatedUser();
        return jobRepository.findByRecruiterId(recruiter.getId());
    }

    @Override
    public Job updateMyJob(String jobId, JobRequest jobRequest) {
        User recruiter = getAuthenticatedUser();
        Job job = getJobById(jobId);

        // Security Check: Ensure the user owns this job
        if (!job.getRecruiterId().equals(recruiter.getId())) {
            throw new AccessDeniedException("You do not have permission to edit this job");
        }

        job.setTitle(jobRequest.getTitle());
        job.setDescription(jobRequest.getDescription());
        job.setCompanyName(jobRequest.getCompanyName());
        job.setLocation(jobRequest.getLocation());

        return jobRepository.save(job);
    }

    @Override
    public void deleteMyJob(String jobId) {
        User recruiter = getAuthenticatedUser();
        Job job = getJobById(jobId);

        // Security Check: Ensure the user owns this job
        if (!job.getRecruiterId().equals(recruiter.getId())) {
            throw new AccessDeniedException("You do not have permission to delete this job");
        }

        jobRepository.delete(job);
    }
}
