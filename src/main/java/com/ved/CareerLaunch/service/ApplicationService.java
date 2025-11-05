package com.ved.CareerLaunch.service;


import com.ved.CareerLaunch.model.Application;
import com.ved.CareerLaunch.model.enums.EApplicationStatus;

import java.util.List;

public interface ApplicationService {

    // --- Applicant ---
    Application applyToJob(String jobId);
    List<Application> getMyApplications();

    // --- Recruiter ---
    List<Application> getApplicationsForJob(String jobId);
    Application updateApplicationStatus(String applicationId, EApplicationStatus status);
}
