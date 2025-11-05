package com.ved.CareerLaunch.service;


import com.ved.CareerLaunch.dto.request.JobRequest;
import com.ved.CareerLaunch.model.Job;

import java.util.List;

public interface JobService {

    // --- Public / Applicant ---
    List<Job> getAllPublicJobs();
    Job getJobById(String jobId);

    // --- Recruiter ---
    Job postJob(JobRequest jobRequest);
    List<Job> getMyPostedJobs();
    Job updateMyJob(String jobId, JobRequest jobRequest);
    void deleteMyJob(String jobId);
}
