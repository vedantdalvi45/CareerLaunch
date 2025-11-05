package com.ved.CareerLaunch.model;


import com.ved.CareerLaunch.model.enums.EApplicationStatus;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.Instant;

@Data
@Document(collection = "applications")
@CompoundIndex(name = "job_applicant_unique", def = "{'jobId': 1, 'applicantId': 1}", unique = true)
public class Application {

    @Id
    private String id;

    private String jobId;       // String ID of the Job

    private String applicantId; // String ID of the Applicant (User)

    private EApplicationStatus status = EApplicationStatus.APPLIED;

    private Instant appliedDate = Instant.now();
}