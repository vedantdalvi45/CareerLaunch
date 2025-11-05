# [cite_start]CareerLaunch API [cite: 1]

> [cite_start]A robust, scalable backend for a modern job and internship finder platform. [cite: 2]

[cite_start]CareerLaunch is a complete Spring Boot application that provides a secure, role-based REST API for a job board. [cite: 3] [cite_start]It connects three key user types: **Applicants** looking for jobs, **Recruiters** posting jobs, and **Admins** moderating the platform. [cite: 4] [cite_start]It's built on a clean, decoupled architecture using Spring Security for authentication and MongoDB for a flexible, scalable database. [cite: 5]

---

## [cite_start]🚀 Key Features [cite: 6]

* [cite_start]**Role-Based Access Control (RBAC):** Three distinct roles (`ROLE_APPLICANT`, `ROLE_RECRUITER`, `ROLE_ADMIN`) with granular permissions. [cite: 7]
* [cite_start]**Secure Authentication:** End-to-end authentication and authorization using Spring Security and JSON Web Tokens (JWT). [cite: 8]
* [cite_start]**Auto-Login on Register:** Users are automatically authenticated and given a token upon successful registration. [cite: 10]
* [cite_start]**Full Job Lifecycle:** Recruiters can perform full CRUD operations (Create, Read, Update, Delete) on job postings. [cite: 11]
* [cite_start]**Application Tracking:** Applicants can apply for jobs and track the status of their applications. [cite: 12]
* [cite_start]**Recruiter Dashboard:** Recruiters can view all applicants for their jobs and update application statuses. [cite: 13]
* [cite_start]**Admin Moderation:** Admins have global permissions to manage users and moderate content (e.g., delete inappropriate jobs). [cite: 14]
* [cite_start]**Scalable Database:** Uses MongoDB as a NoSQL database, perfect for handling diverse and large-scale data. [cite: 15]

---

## [cite_start]🛠️ Tech Stack & Architecture [cite: 16]

### [cite_start]Tech Stack [cite: 17]

* [cite_start]**Java 17+** [cite: 18]
* [cite_start]**Spring Boot 3.x** [cite: 19]
* [cite_start]**Spring Security 6.x:** For JWT-based authentication and method-level security (`@PreAuthorize`). [cite: 21]
* [cite_start]**Spring Data MongoDB:** For repository and database interaction. [cite: 22]
* [cite_start]**MongoDB Atlas:** Cloud-hosted NoSQL database. [cite: 23]
* [cite_start]**Lombok:** To reduce boilerplate code. [cite: 24]
* [cite_start]**Maven:** For dependency management. [cite: 25]
* [cite_start]**jjwt:** Java library for creating and validating JSON Web Tokens. [cite: 26]

### [cite_start]Architecture [cite: 27]

[cite_start]The application follows a standard 3-Layer Architecture for a clean separation of concerns: [cite: 28]

1.  [cite_start]**Controller Layer (`/controller`):** [cite: 29]
    * [cite_start]Handles all incoming HTTP requests. [cite: 31]
    * [cite_start]Validates request bodies using DTOs (`/dto`). [cite: 32]
    * [cite_start]Maps requests to the appropriate service methods. [cite: 33]
    * [cite_start]Serializes service responses to JSON. [cite: 34]
2.  [cite_start]**Service Layer (`/service`):** [cite: 35]
    * [cite_start]Contains all business logic. [cite: 38]
    * [cite_start]Coordinates between repositories. [cite: 39]
    * [cite_start]Handles data validation, manipulation, and transformations. [cite: 40]
    * [cite_start]Throws business-specific exceptions. [cite: 41]
    * [cite_start]Manages authentication logic (`AuthService`). [cite: 42]
3.  [cite_start]**Repository Layer (`/repository`):** [cite: 43]
    * [cite_start]Defines interfaces that extend Spring Data's `MongoRepository`. [cite: 45]
    * [cite_start]Handles all database query logic. [cite: 45]

[cite_start]Security is handled by a `JwtAuthenticationFilter` which intercepts every request. [cite: 46] [cite_start]It validates the `Authorization: Bearer <token>` header and sets the user's authentication in the `SecurityContext`. [cite: 47] [cite_start]Endpoints are then secured using `@PreAuthorize` annotations. [cite: 48]

---

## [cite_start]⚙️ Configuration [cite: 49]

[cite_start]To run the project locally, you must provide configuration in `src/main/resources/application.yml`: [cite: 50]

```yaml
server: [cite: 51]
  port: 8080 [cite: 52]

# Configure your Mongo Atlas connection string [cite: 53]
spring: [cite: 54]
  data: [cite: 55]
    mongodb: [cite: 56]
      uri: mongodb+srv://<username>:<password>@<your-cluster-url>/careerlaunch?retryWrites=true&w=majority [cite: 57, 58]

# Custom properties [cite: 59]
app: [cite: 60]
  jwt: [cite: 61]
    # This MUST be kept secret. [cite: 62]
    # Generate a secure 64-byte, Base64-encoded secret. [cite: 63]
    secret: bXluYW1laXNqYW1lc2JvbmRiYW5kaWFtYnJpdGlzaHNweWFuZGlsaWtlYmFzZWJhbGxzYW5kcGFzc3dvcmRpc3Bhc3N3b3Jk [cite: 64, 65, 66]
    # Token expiration time in milliseconds (86400000 = 24 hours) [cite: 67]
    expiration-ms: 86400000 [cite: 67]
