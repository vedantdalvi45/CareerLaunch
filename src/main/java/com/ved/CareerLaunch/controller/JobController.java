package com.ved.CareerLaunch.controller;


import com.ved.CareerLaunch.dto.request.JobRequest;
import com.ved.CareerLaunch.model.Job;
import com.ved.CareerLaunch.service.JobService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/jobs")
@RequiredArgsConstructor
public class JobController {

    private final JobService jobService;

    // --- PUBLIC ENDPOINTS (for Applicants) ---

    @GetMapping
    public List<Job> getAllJobs() {
        return jobService.getAllPublicJobs();
    }

    @GetMapping("/{jobId}")
    public ResponseEntity<Job> getJobById(@PathVariable String jobId) {
        return ResponseEntity.ok(jobService.getJobById(jobId));
    }

    // --- RECRUITER ENDPOINTS ---

    @PostMapping
    @PreAuthorize("hasRole('ROLE_RECRUITER')")
    public ResponseEntity<Job> postJob(@Valid @RequestBody JobRequest jobRequest) {
        return ResponseEntity.ok(jobService.postJob(jobRequest));
    }

    @GetMapping("/my-jobs")
    @PreAuthorize("hasRole('ROLE_RECRUITER')")
    public List<Job> getRecruiterJobs() {
        return jobService.getMyPostedJobs();
    }

    @PutMapping("/{jobId}")
    @PreAuthorize("hasRole('ROLE_RECRUITER')")
    public ResponseEntity<Job> updateJob(@PathVariable String jobId, @Valid @RequestBody JobRequest jobRequest) {
        return ResponseEntity.ok(jobService.updateMyJob(jobId, jobRequest));
    }

    @DeleteMapping("/{jobId}")
    @PreAuthorize("hasRole('ROLE_RECRUITER')")
    public ResponseEntity<?> deleteJob(@PathVariable String jobId) {
        jobService.deleteMyJob(jobId);
        return ResponseEntity.ok("Job deleted successfully");
    }
}