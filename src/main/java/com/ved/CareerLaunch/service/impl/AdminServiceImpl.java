package com.ved.CareerLaunch.service.impl;

import com.ved.CareerLaunch.model.User;
import com.ved.CareerLaunch.repository.ApplicationRepository;
import com.ved.CareerLaunch.repository.JobRepository;
import com.ved.CareerLaunch.repository.UserRepository;
import com.ved.CareerLaunch.service.AdminService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

    private final UserRepository userRepository;
    private final JobRepository jobRepository;
    private final ApplicationRepository applicationRepository;

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public void deleteUser(String userId) {
        // You might want to also delete related jobs or applications
        // or handle them via cascading logic if your DB supports it.
        // For Mongo, we delete manually.
        userRepository.deleteById(userId);

        // Also delete their jobs and applications
        jobRepository.deleteAll(jobRepository.findByRecruiterId(userId));
        applicationRepository.deleteAll(applicationRepository.findByApplicantId(userId));
    }

    @Override
    public void deleteJob(String jobId) {
        // Admin can delete any job, no ownership check needed
        jobRepository.deleteById(jobId);
        // Also delete all applications for this job
        applicationRepository.deleteAll(applicationRepository.findByJobId(jobId));
    }
}
