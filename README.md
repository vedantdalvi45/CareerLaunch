# CareerLaunch API

[![Build Status](https://img.shields.io/badge/build-passing-brightgreen?style=flat-square)](https://github.com/your-username/careerlaunch)
[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg?style=flat-square)](https://opensource.org/licenses/MIT)

A robust, scalable backend for a modern job and internship finder platform.

**CareerLaunch** is a complete Spring Boot application that provides a secure, role-based REST API for a job board. It connects three key user types: **Applicants** looking for jobs, **Recruiters** posting jobs, and **Admins** moderating the platform. It's built on a clean, decoupled architecture using Spring Security for authentication and MongoDB for a flexible, scalable database.

## 🚀 Key Features

* **Role-Based Access Control (RBAC):** Three distinct roles (`ROLE_APPLICANT`, `ROLE_RECRUITER`, `ROLE_ADMIN`) with granular permissions.
* **Secure Authentication:** End-to-end authentication and authorization using Spring Security and **JSON Web Tokens (JWT)**.
* **Auto-Login on Register:** Users are automatically authenticated and given a token upon successful registration.
* **Full Job Lifecycle:** Recruiters can perform full CRUD operations (Create, Read, Update, Delete) on job postings.
* **Application Tracking:** Applicants can apply for jobs and track the status of their applications.
* **Recruiter Dashboard:** Recruiters can view all applicants for their jobs and update application statuses.
* **Admin Moderation:** Admins have global permissions to manage users and moderate content (e.g., delete inappropriate jobs).
* **Scalable Database:** Uses MongoDB as a NoSQL database, perfect for handling diverse and large-scale data.

## 🏛️ Tech Stack & Architecture

### Tech Stack

* **Java 17+**
* **Spring Boot 3.x**
* **Spring Security 6.x:** For JWT-based authentication and method-level security (`@PreAuthorize`).
* **Spring Data MongoDB:** For repository and database interaction.
* **MongoDB Atlas:** Cloud-hosted NoSQL database.
* **Lombok:** To reduce boilerplate code.
* **Maven:** For dependency management.
* **jjwt:** Java library for creating and validating JSON Web Tokens.

### Architecture

The application follows a standard **3-Layer Architecture** for a clean separation of concerns:

1.  **Controller Layer (`/controller`):**
    * Handles all incoming HTTP requests.
    * Validates request bodies using DTOs (`/dto`).
    * Maps requests to the appropriate service methods.
    * Serializes service responses to JSON.

2.  **Service Layer (`/service`):**
    * Contains all business logic.
    * Coordinates between repositories.
    * Handles data validation, manipulation, and transformations.
    * Throws business-specific exceptions.
    * Manages authentication logic (`AuthService`).

3.  **Repository Layer (`/repository`):**
    * Defines interfaces that extend Spring Data's `MongoRepository`.
    * Handles all database query logic.



**Security** is handled by a `JwtAuthenticationFilter` which intercepts every request. It validates the `Authorization: Bearer <token>` header and sets the user's authentication in the `SecurityContext`. Endpoints are then secured using `@PreAuthorize` annotations.

## 🔧 Configuration

To run the project locally, you must provide configuration in `src/main/resources/application.yml`:

```yml
server:
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
