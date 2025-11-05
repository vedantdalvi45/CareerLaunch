package com.ved.CareerLaunch.repository;

import com.ved.CareerLaunch.model.Job;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface JobRepository extends MongoRepository<Job, String> {

    // Finds all jobs posted by a specific recruiter
    List<Job> findByRecruiterId(String recruiterId);
}
