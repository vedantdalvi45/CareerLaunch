package com.ved.CareerLaunch.service.impl;

import com.ved.CareerLaunch.model.Application;
import com.ved.CareerLaunch.model.Job;
import com.ved.CareerLaunch.model.User;
import com.ved.CareerLaunch.model.enums.EApplicationStatus;
import com.ved.CareerLaunch.repository.ApplicationRepository;
import com.ved.CareerLaunch.repository.JobRepository;
import com.ved.CareerLaunch.repository.UserRepository;
import com.ved.CareerLaunch.service.ApplicationService;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ApplicationServiceImpl implements ApplicationService {

    private final ApplicationRepository applicationRepository;
    private final JobRepository jobRepository;
    private final UserRepository userRepository;

    private User getAuthenticatedUser() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Authenticated user not found"));
    }

    // --- APPLICANT METHODS ---

    @Override
    public Application applyToJob(String jobId) {
        User applicant = getAuthenticatedUser();

        // Check if job exists
        jobRepository.findById(jobId)
                .orElseThrow(() -> new RuntimeException("Job not found"));

        // Check if already applied
        if (applicationRepository.findByJobId(jobId).stream()
                .anyMatch(app -> app.getApplicantId().equals(applicant.getId()))) {
            throw new RuntimeException("You have already applied to this job");
        }

        Application application = new Application();
        application.setApplicantId(applicant.getId());
        application.setJobId(jobId);
        application.setStatus(EApplicationStatus.APPLIED);
        application.setAppliedDate(Instant.now());

        return applicationRepository.save(application);
    }

    @Override
    public List<Application> getMyApplications() {
        User applicant = getAuthenticatedUser();
        return applicationRepository.findByApplicantId(applicant.getId());
    }

    // --- RECRUITER METHODS ---

    @Override
    public List<Application> getApplicationsForJob(String jobId) {
        User recruiter = getAuthenticatedUser();
        Job job = jobRepository.findById(jobId)
                .orElseThrow(() -> new RuntimeException("Job not found"));

        // Security Check: Ensure recruiter owns the job
        if (!job.getRecruiterId().equals(recruiter.getId())) {
            throw new AccessDeniedException("You do not have permission to view these applications");
        }

        return applicationRepository.findByJobId(jobId);
    }

    @Override
    public Application updateApplicationStatus(String applicationId, EApplicationStatus status) {
        User recruiter = getAuthenticatedUser();
        Application application = applicationRepository.findById(applicationId)
                .orElseThrow(() -> new RuntimeException("Application not found"));

        Job job = jobRepository.findById(application.getJobId())
                .orElseThrow(() -> new RuntimeException("Job not found"));

        // Security Check: Ensure recruiter owns the job this application is for
        if (!job.getRecruiterId().equals(recruiter.getId())) {
            throw new AccessDeniedException("You do not have permission to update this application");
        }

        application.setStatus(status);
        return applicationRepository.save(application);
    }
}
