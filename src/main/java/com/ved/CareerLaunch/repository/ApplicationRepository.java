package com.ved.CareerLaunch.repository;


import com.ved.CareerLaunch.model.Application;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface ApplicationRepository extends MongoRepository<Application, String> {

    // Finds all applications submitted by a specific applicant
    List<Application> findByApplicantId(String applicantId);

    // Finds all applications for a specific job
    List<Application> findByJobId(String jobId);
}
