# 🚀 CareerLaunch API
[![Build Status](https://img.shields.io/badge/build-passing-brightgreen?style=flat-square)](https://github.com/your-username/careerlaunch)
[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg?style=flat-square)](https://opensource.org/licenses/MIT)

🌟 A robust, scalable backend for a modern job and internship finder platform, connecting employers with top talent through a secure, role-based REST API.

## 📋 Overview
CareerLaunch is a complete Spring Boot application that provides a secure, role-based REST API for a job board. It connects three key user types: 
👨‍💼 **Applicants** looking for jobs, 
👔 **Recruiters** posting jobs, and 
👮 **Admins** moderating the platform. It's built on a clean, decoupled architecture using Spring Security for authentication and MongoDB for a flexible, scalable database.

## 🎯 Key Features
* **🔒 Role-Based Access Control (RBAC):** Three distinct roles (`ROLE_APPLICANT`, `ROLE_RECRUITER`, `ROLE_ADMIN`) with granular permissions
* **🔐 Secure Authentication:** End-to-end authentication and authorization using Spring Security and **JSON Web Tokens (JWT)**
* **📈 Auto-Login on Register:** Users are automatically authenticated and given a token upon successful registration
* **📋 Full Job Lifecycle:** Recruiters can perform full CRUD operations on job postings
* **📊 Application Tracking:** Applicants can apply for jobs and track the status of their applications
* **📈 Recruiter Dashboard:** Recruiters can view all applicants for their jobs and update application statuses
* **🛡️ Admin Moderation:** Admins have global permissions to manage users and moderate content
* **📚 Scalable Database:** Uses MongoDB as a NoSQL database, perfect for handling diverse and large-scale data

## 🛠️ Tech Stack & Architecture

### 📦 Tech Stack
* **🔷 Java 17+**
* **🚀 Spring Boot 3.x**
* **🔒 Spring Security 6.x:** For JWT-based authentication and method-level security (`@PreAuthorize`)
* **📱 Spring Data MongoDB:** For repository and database interaction
* **🌐 MongoDB Atlas:** Cloud-hosted NoSQL database
* **💼 Lombok:** To reduce boilerplate code
* **📦 Maven:** For dependency management
* **🔑 jjwt:** Java library for creating and validating JSON Web Tokens

### 🏗️ Architecture
The application follows a standard **3-Layer Architecture** for a clean separation of concerns:

1. **📱 Controller Layer**
   - Handles all incoming HTTP requests
   - Validates request bodies using DTOs
   - Maps requests to the appropriate service methods
   - Serializes service responses to JSON

2. **🧮 Service Layer**
   - Contains all business logic
   - Coordinates between repositories
   - Handles data validation, manipulation, and transformations
   - Throws business-specific exceptions
   - Manages authentication logic (`AuthService`)

3. **📂 Repository Layer**
   - Defines interfaces that extend Spring Data's `MongoRepository`
   - Handles all database query logic

## 📝 Configuration
To run the project locally, you must provide configuration in `src/main/resources/application.yml`:

```yml
server:
  port: 8080

spring:
  data:
    mongodb:
      uri: mongodb+srv://<username>:<password>@<your-cluster-url>/careerlaunch?retryWrites=true&w=majority

app:
  jwt:
    # This MUST be kept secret. 
    # Generate a secure 64-byte, Base64-encoded secret.
    secret: bXluYW1laXNqYW1lc2JvbmRiYW5kaWFtYnJpdGlzaHNweWFuZGlsaWtlYmFzZWJhbGxzYW5kcGFzc3dvcmRpc3Bhc3N3b3Jk
 
    # Token expiration time in milliseconds (86400000 = 24 hours)
    expiration-ms: 86400000
```

## 📱 API Documentation

All requests and responses are in JSON format. A successful authentication (login/register) returns a JWT token which must be included in the header for all secured endpoints:

```http
Authorization: Bearer <your-jwt-token>
```

### 📝 Authentication (Public)

| Method | Endpoint | Description |
| --- | --- | --- |
| `POST` | `/api/auth/register` | Registers a new user (Applicant, Recruiter, or Admin) |
| `POST` | `/api/auth/login` | Authenticates an existing user |

#### Register Request Body
```json
{
  "firstName": "Sonia",
  "lastName": "Applicant",
  "email": "sonia.applicant@example.com",
  "password": "password123",
  "role": "ROLE_APPLICANT"
}
```

### 🌐 Public Job Endpoints (No Auth Required)

| Method | Endpoint | Description |
| --- | --- | --- |
| `GET` | `/api/jobs` | Get a list of all active job postings |
| `GET` | `/api/jobs/{jobId}` | Get the details for a single job by its ID |

### 👨‍🎓 Applicant Endpoints

Requires `ROLE_APPLICANT` token.

| Method | Endpoint | Description |
| --- | --- | --- |
| `POST` | `/api/jobs/{jobId}/apply` | Submits an application for a specific job |
| `GET` | `/api/applications/my-applications` | Returns a list of all applications submitted by the user |

### 👔 Recruiter Endpoints

Requires `ROLE_RECRUITER` token.

| Method | Endpoint | Description |
| --- | --- | --- |
| `POST` | `/api/jobs` | Creates a new job posting |
| `GET` | `/api/jobs/my-jobs` | Returns a list of all jobs posted by the user |
| `PUT` | `/api/jobs/{jobId}` | Updates the details of a job owned by the user |
| `DELETE` | `/api/jobs/{jobId}` | Deletes a job owned by the user |
| `GET` | `/api/jobs/{jobId}/applications` | Returns a list of all applications for a specific job |
| `PUT` | `/api/applications/{applicationId}/status` | Updates the status of an application |

#### Job Request Body
```json
{
  "title": "Senior Spring Boot Developer",
  "description": "Looking for a skilled developer with 5+ years of experience...",
  "companyName": "Tech Solutions Inc.",
  "location": "Remote"
}
```

### 🛡️ Admin Endpoints

Requires `ROLE_ADMIN` token.

| Method | Endpoint | Description |
| --- | --- | --- |
| `GET` | `/api/admin/users` | Returns a list of all users in the database |
| `DELETE` | `/api/admin/users/{userId}` | Deletes any user by their ID |
| `DELETE` | `/api/admin/jobs/{jobId}` | Deletes any job by its ID (for moderation)
