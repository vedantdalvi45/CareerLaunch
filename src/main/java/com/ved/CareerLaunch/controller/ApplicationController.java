package com.ved.CareerLaunch.controller;

import com.ved.CareerLaunch.model.Application;
import com.ved.CareerLaunch.model.enums.EApplicationStatus;
import com.ved.CareerLaunch.service.ApplicationService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ApplicationController {

    private final ApplicationService applicationService;

    // --- APPLICANT ENDPOINTS ---

    @PostMapping("/jobs/{jobId}/apply")
    @PreAuthorize("hasRole('ROLE_APPLICANT')")
    public ResponseEntity<Application> applyToJob(@PathVariable String jobId) {
        return ResponseEntity.ok(applicationService.applyToJob(jobId));
    }

    @GetMapping("/applications/my-applications")
    @PreAuthorize("hasRole('ROLE_APPLICANT')")
    public List<Application> getMyApplications() {
        return applicationService.getMyApplications();
    }

    // --- RECRUITER ENDPOINTS ---

    @GetMapping("/jobs/{jobId}/applications")
    @PreAuthorize("hasRole('ROLE_RECRUITER')")
    public List<Application> getApplicationsForJob(@PathVariable String jobId) {
        return applicationService.getApplicationsForJob(jobId);
    }

    @PutMapping("/applications/{applicationId}/status")
    @PreAuthorize("hasRole('ROLE_RECRUITER')")
    public ResponseEntity<Application> updateStatus(
            @PathVariable String applicationId,
            @RequestBody Map<String, String> statusUpdate
    ) {
        EApplicationStatus status = EApplicationStatus.valueOf(statusUpdate.get("status"));
        return ResponseEntity.ok(applicationService.updateApplicationStatus(applicationId, status));
    }
}
