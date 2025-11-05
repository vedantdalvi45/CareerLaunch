package com.ved.CareerLaunch.model;


import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.Instant;

@Data
@Document(collection = "jobs")
public class Job {

    @Id
    private String id;

    private String recruiterId; // The String ID of the User who posted this

    private String title;

    private String description;

    private String companyName;

    private String location;

    private Instant postedDate = Instant.now();
}