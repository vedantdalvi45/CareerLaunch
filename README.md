CareerLaunch APIA robust, scalable backend for a modern job and internship finder platform.CareerLaunch is a complete Spring Boot application that provides a secure, role-based REST API for a job board. It connects three key user types: Applicants looking for jobs, Recruiters posting jobs, and Admins moderating the platform. It's built on a clean, decoupled architecture using Spring Security for authentication and MongoDB for a flexible, scalable database.🚀 Key FeaturesRole-Based Access Control (RBAC): Three distinct roles (ROLE_APPLICANT, ROLE_RECRUITER, ROLE_ADMIN) with granular permissions.Secure Authentication: End-to-end authentication and authorization using Spring Security and JSON Web Tokens (JWT).Auto-Login on Register: Users are automatically authenticated and given a token upon successful registration.Full Job Lifecycle: Recruiters can perform full CRUD operations (Create, Read, Update, Delete) on job postings.Application Tracking: Applicants can apply for jobs and track the status of their applications.Recruiter Dashboard: Recruiters can view all applicants for their jobs and update application statuses.Admin Moderation: Admins have global permissions to manage users and moderate content (e.g., delete inappropriate jobs).Scalable Database: Uses MongoDB as a NoSQL database, perfect for handling diverse and large-scale data.🏛️ Tech Stack & ArchitectureTech StackJava 17+Spring Boot 3.xSpring Security 6.x: For JWT-based authentication and method-level security (@PreAuthorize).Spring Data MongoDB: For repository and database interaction.MongoDB Atlas: Cloud-hosted NoSQL database.Lombok: To reduce boilerplate code.Maven: For dependency management.jjwt: Java library for creating and validating JSON Web Tokens.ArchitectureThe application follows a standard 3-Layer Architecture for a clean separation of concerns:Controller Layer (/controller):Handles all incoming HTTP requests.Validates request bodies using DTOs (/dto).Maps requests to the appropriate service methods.Serializes service responses to JSON.Service Layer (/service):Contains all business logic.Coordinates between repositories.Handles data validation, manipulation, and transformations.Throws business-specific exceptions.Manages authentication logic (AuthService).Repository Layer (/repository):Defines interfaces that extend Spring Data's MongoRepository.Handles all database query logic.Security is handled by a JwtAuthenticationFilter which intercepts every request. It validates the Authorization: Bearer <token> header and sets the user's authentication in the SecurityContext. Endpoints are then secured using @PreAuthorize annotations.🔧 ConfigurationTo run the project locally, you must provide configuration in src/main/resources/application.yml:server:
  port: 8080

# Configure your Mongo Atlas connection string
spring:
  data:
    mongodb:
      uri: mongodb+srv://<username>:<password>@<your-cluster-url>/careerlaunch?retryWrites=true&w=majority

# Custom properties
app:
  jwt:
    # This MUST be kept secret. 
    # Generate a secure 64-byte, Base64-encoded secret.
    secret: bXluYW1laXNqYW1lc2JvbmRiYW5kaWFtYnJpdGlzaHNweWFuZGlsaWtlYmFzZWJhbGxzYW5kcGFzc3dvcmRpc3Bhc3N3b3Jk
    
    # Token expiration time in milliseconds (86400000 = 24 hours)
    expiration-ms: 86400000
🔌 API Endpoint DocumentationAll requests and responses are in JSON format. A successful authentication (login/register) returns a JWT token which must be included in the header for all secured endpoints.Header: Authorization: Bearer <your-jwt-token>🔑 Authentication (Public)MethodEndpointDescriptionPOST/api/auth/registerRegisters a new user (Applicant, Recruiter, or Admin).POST/api/auth/loginAuthenticates an existing user.<details> <summary><b>JSON for <code>POST /api/auth/register</code></b></summary>{
  "firstName": "Sonia",
  "lastName": "Applicant",
  "email": "sonia.applicant@example.com",
  "password": "password123",
  "role": "ROLE_APPLICANT"
}
Note: Change role to ROLE_RECRUITER or ROLE_ADMIN to create other user types.</details><details> <summary><b>JSON for <code>POST /api/auth/login</code></b></summary>{
  "email": "sonia.applicant@example.com",
  "password": "password123"
}
</details>🌍 Public Job Endpoints (No Auth Required)MethodEndpointDescriptionGET/api/jobsGet a list of all active job postings.GET/api/jobs/{jobId}Get the details for a single job by its ID.🧑‍🎓 Applicant EndpointsAuth: Requires ROLE_APPLICANT token.MethodEndpointDescriptionPOST/api/jobs/{jobId}/applySubmits an application for a specific job.GET/api/applications/my-applicationsReturns a list of all applications submitted by the user.👔 Recruiter EndpointsAuth: Requires ROLE_RECRUITER token.MethodEndpointDescriptionPOST/api/jobsCreates a new job posting.GET/api/jobs/my-jobsReturns a list of all jobs posted by the user.PUT/api/jobs/{jobId}Updates the details of a job owned by the user.DELETE/api/jobs/{jobId}Deletes a job owned by the user.GET/api/jobs/{jobId}/applicationsReturns a list of all applications for a specific job.PUT/api/applications/{applicationId}/statusUpdates the status of an application.<details> <summary><b>JSON for <code>POST /api/jobs</code> or <code>PUT /api/jobs/{jobId}</code></b></summary>{
  "title": "Senior Spring Boot Developer",
  "description": "Looking for a skilled developer with 5+ years of experience...",
  "companyName": "Tech Solutions Inc.",
  "location": "Remote"
}
</details><details> <summary><b>JSON for <code>PUT /api/applications/{applicationId}/status</code></b></summary>{
  "status": "UNDER_REVIEW"
}
Valid statuses: APPLIED, VIEWED, UNDER_REVIEW, REJECTED, HIRED</details>🛠️ Admin EndpointsAuth: Requires ROLE_ADMIN token.MethodEndpointDescriptionGET/api/admin/usersReturns a list of all users in the database.DELETE/api/admin/users/{userId}Deletes any user by their ID.DELETE/api/admin/jobs/{jobId}Deletes any job by its ID (for moderation).
