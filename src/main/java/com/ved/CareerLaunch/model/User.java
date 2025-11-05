package com.ved.CareerLaunch.model;


import com.ved.CareerLaunch.model.enums.ERole;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.Instant;

@Data
@Document(collection = "users")
public class User {

    @Id
    private String id;

    private String firstName;

    private String lastName;

    @Indexed(unique = true)
    private String email;

    private String passwordHash; // Store the hashed password

    private ERole role;

    private String resumeUrl; // Link to cloud-stored resume

    private Instant createdAt = Instant.now();
}
