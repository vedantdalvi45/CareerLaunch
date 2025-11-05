<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>CareerLaunch API Documentation</title>
    <style>
        body {
            font-family: -apple-system, BlinkMacSystemFont, "Segoe UI", Roboto, Helvetica, Arial, sans-serif;
            line-height: 1.6;
            background-color: #f9f9f9;
            color: #333;
            margin: 0;
            padding: 0;
        }
        main {
            max-width: 960px;
            margin: 20px auto;
            padding: 20px;
            background-color: #ffffff;
            border-radius: 8px;
            box-shadow: 0 2px 8px rgba(0,0,0,0.05);
        }
        h1, h2, h3 {
            border-bottom: 2px solid #eee;
            padding-bottom: 10px;
            margin-top: 30px;
        }
        h1 {
            font-size: 2.5em;
        }
        h2 {
            font-size: 2em;
        }
        h3 {
            font-size: 1.5em;
        }
        code {
            font-family: "SFMono-Regular", Consolas, "Liberation Mono", Menlo, Courier, monospace;
            background-color: #f0f0f0;
            padding: 0.2em 0.4em;
            border-radius: 4px;
            font-size: 0.9em;
        }
        pre {
            background-color: #f6f8fa;
            border: 1px solid #ddd;
            border-radius: 6px;
            padding: 16px;
            overflow: auto;
        }
        pre code {
            background-color: transparent;
            padding: 0;
            font-size: 1em;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            margin: 20px 0;
            display: block;
            overflow-x: auto;
        }
        th, td {
            border: 1px solid #ddd;
            padding: 12px;
            text-align: left;
        }
        th {
            background-color: #f6f6f6;
            font-weight: 600;
        }
        tr:nth-child(even) {
            background-color: #f9f9f9;
        }
        details {
            margin: 15px 0;
            background-color: #fcfcfc;
            border: 1px solid #eee;
            border-radius: 6px;
        }
        summary {
            padding: 12px;
            cursor: pointer;
            font-weight: 600;
            outline: none;
        }
        details[open] {
            padding-bottom: 12px;
        }
        details pre {
            margin: 0 12px;
        }
        ul, ol {
            padding-left: 30px;
        }
        li {
            margin-bottom: 8px;
        }
        img {
            max-width: 100%;
        }
        p {
            margin: 1em 0;
        }
        hr {
            border: 0;
            border-top: 2px solid #eee;
            margin: 30px 0;
        }
    </style>
</head>
<body>
    <main>
        <h1>CareerLaunch API</h1>
        <p>
            <a href="https://github.com/your-username/careerlaunch">
                <img src="https://img.shields.io/badge/build-passing-brightgreen?style=flat-square" alt="Build Status">
            </a>
            <a href="https://opensource.org/licenses/MIT">
                <img src="https://img.shields.io/badge/License-MIT-yellow.svg?style=flat-square" alt="License: MIT">
            </a>
        </p>

        <p>A robust, scalable backend for a modern job and internship finder platform.</p>
        <p>
            <strong>CareerLaunch</strong> is a complete Spring Boot application that provides a secure, role-based REST API for a job board. It connects three key user types: <strong>Applicants</strong> looking for jobs, <strong>Recruiters</strong> posting jobs, and <strong>Admins</strong> moderating the platform. It's built on a clean, decoupled architecture using Spring Security for authentication and MongoDB for a flexible, scalable database.
        </p>

        <h2>🚀 Key Features</h2>
        <ul>
            <li><strong>Role-Based Access Control (RBAC):</strong> Three distinct roles (<code>ROLE_APPLICANT</code>, <code>ROLE_RECRUITER</code>, <code>ROLE_ADMIN</code>) with granular permissions.</li>
            <li><strong>Secure Authentication:</strong> End-to-end authentication and authorization using Spring Security and <strong>JSON Web Tokens (JWT)</strong>.</li>
            <li><strong>Auto-Login on Register:</strong> Users are automatically authenticated and given a token upon successful registration.</li>
            <li><strong>Full Job Lifecycle:</strong> Recruiters can perform full CRUD operations (Create, Read, Update, Delete) on job postings.</li>
            <li><strong>Application Tracking:</strong> Applicants can apply for jobs and track the status of their applications.</li>
            <li><strong>Recruiter Dashboard:</strong> Recruiters can view all applicants for their jobs and update application statuses.</li>
            <li><strong>Admin Moderation:</strong> Admins have global permissions to manage users and moderate content (e.g., delete inappropriate jobs).</li>
            <li><strong>Scalable Database:</strong> Uses MongoDB as a NoSQL database, perfect for handling diverse and large-scale data.</li>
        </ul>

        <h2>🏛️ Tech Stack & Architecture</h2>
        <h3>Tech Stack</h3>
        <ul>
            <li><strong>Java 17+</strong></li>
            <li><strong>Spring Boot 3.x</strong></li>
            <li><strong>Spring Security 6.x:</strong> For JWT-based authentication and method-level security (<code>@PreAuthorize</code>).</li>
            <li><strong>Spring Data MongoDB:</strong> For repository and database interaction.</li>
            <li><strong>MongoDB Atlas:</strong> Cloud-hosted NoSQL database.</li>
            <li><strong>Lombok:</strong> To reduce boilerplate code.</li>
            <li><strong>Maven:</strong> For dependency management.</li>
            <li><strong>jjwt:</strong> Java library for creating and validating JSON Web Tokens.</li>
        </ul>

        <h3>Architecture</h3>
        <p>The application follows a standard <strong>3-Layer Architecture</strong> for a clean separation of concerns:</p>
        <ol>
            <li>
                <strong>Controller Layer (<code>/controller</code>):</strong>
                <ul>
                    <li>Handles all incoming HTTP requests.</li>
                    <li>Validates request bodies using DTOs (<code>/dto</code>).</li>
                    <li>Maps requests to the appropriate service methods.</li>
                    <li>Serializes service responses to JSON.</li>
                </ul>
            </li>
            <li>
                <strong>Service Layer (<code>/service</code>):</strong>
                <ul>
                    <li>Contains all business logic.</li>
                    <li>Coordinates between repositories.</li>
                    <li>Handles data validation, manipulation, and transformations.</li>
                    <li>Throws business-specific exceptions.</li>
                    <li>Manages authentication logic (<code>AuthService</code>).</li>
                </ul>
            </li>
            <li>
                <strong>Repository Layer (<code>/repository</code>):</strong>
                <ul>
                    <li>Defines interfaces that extend Spring Data's <code>MongoRepository</code>.</li>
                    <li>Handles all database query logic.</li>
                </ul>
            </li>
        </ol>
        <p>
            <strong>Security</strong> is handled by a <code>JwtAuthenticationFilter</code> which intercepts every request. It validates the <code>Authorization: Bearer &lt;token&gt;</code> header and sets the user's authentication in the <code>SecurityContext</code>. Endpoints are then secured using <code>@PreAuthorize</code> annotations.
        </p>

        <h2>🔧 Configuration</h2>
        <p>To run the project locally, you must provide configuration in <code>src/main/resources/application.yml</code>:</p>
        <pre><code class="lang-yml">server:
  port: 8080

# Configure your Mongo Atlas connection string
spring:
  data:
    mongodb:
      uri: mongodb+srv://&lt;username&gt;:&lt;password&gt;@&lt;your-cluster-url&gt;/careerlaunch?retryWrites=true&w=majority

# Custom properties
app:
  jwt:
    # This MUST be kept secret. 
    # Generate a secure 64-byte, Base64-encoded secret.
    secret: bXluYW1laXNqYW1lc2JvbmRiYW5kaWFtYnJpdGlzaHNweWFuZGlsaWtlYmFzZWJhbGxzYW5kcGFzc3dvcmRpc3Bhc3N3b3Jk
    
    # Token expiration time in milliseconds (86400000 = 24 hours)
    expiration-ms: 86400000
</code></pre>

        <!-- This is the start of the original api_docs.html content -->
        <hr>
        <h2 id="api-endpoint-documentation">🔌 API Endpoint Documentation</h2>
        <p>
          All requests and responses are in JSON format. A successful authentication (login/register) returns a JWT token which must be included in the header for all secured endpoints.
        </p>
        <p>
          <strong>Header:</strong> <code>Authorization: Bearer &lt;your-jwt-token&gt;</code>
        </p>
        <hr />

        <!-- Authentication Section -->
        <h3 id="authentication-public">🔑 Authentication (Public)</h3>
        <table>
          <thead>
            <tr>
              <th>Method</th>
              <th>Endpoint</th>
              <th>Description</th>
            </tr>
          </thead>
          <tbody>
            <tr>
              <td><code>POST</code></td>
              <td><code>/api/auth/register</code></td>
              <td>Registers a new user (Applicant, Recruiter, or Admin).</td>
            </tr>
            <tr>
              <td><code>POST</code></td>
              <td><code>/api/auth/login</code></td>
              <td>Authenticates an existing user.</td>
            </tr>
          </tbody>
        </table>

        <details>
          <summary><strong>JSON for <code>POST /api/auth/register</code></strong></summary>
          <pre><code>{
  "firstName": "Sonia",
  "lastName": "Applicant",
  "email": "sonia.applicant@example.com",
  "password": "password123",
  "role": "ROLE_APPLICANT"
}</code></pre>
          <small><em>Note: Change <code>role</code> to <code>ROLE_RECRUITER</code> or <code>ROLE_ADMIN</code> to create other user types.</em></small>
        </details>

        <details>
          <summary><strong>JSON for <code>POST /api/auth/login</code></strong></summary>
          <pre><code>{
  "email": "sonia.applicant@example.com",
  "password": "password123"
}</code></pre>
        </details>

        <hr />

        <!-- Public Job Endpoints Section -->
        <h3 id="public-job-endpoints-no-auth-required">🌍 Public Job Endpoints (No Auth Required)</h3>
        <table>
          <thead>
            <tr>
              <th>Method</th>
              <th>Endpoint</th>
              <th>Description</th>
            </tr>
          </thead>
          <tbody>
            <tr>
              <td><code>GET</code></td>
              <td><code>/api/jobs</code></td>
              <td>Get a list of all active job postings.</td>
            </tr>
            <tr>
              <td><code>GET</code></td>
              <td><code>/api/jobs/{jobId}</code></td>
              <td>Get the details for a single job by its ID.</td>
            </tr>
          </tbody>
        </table>

        <hr />

        <!-- Applicant Endpoints Section -->
        <h3 id="applicant-endpoints">🧑‍🎓 Applicant Endpoints</h3>
        <p><strong>Auth:</strong> Requires <code>ROLE_APPLICANT</code> token.</p>
        <table>
          <thead>
            <tr>
              <th>Method</th>
              <th>Endpoint</th>
              <th>Description</th>
            </tr>
          </thead>
          <tbody>
            <tr>
              <td><code>POST</code></td>
              <td><code>/api/jobs/{jobId}/apply</code></td>
              <td>Submits an application for a specific job.</td>
            </tr>
            <tr>
              <td><code>GET</code></td>
              <td><code>/api/applications/my-applications</code></td>
              <td>Returns a list of all applications submitted by the user.</td>
            </tr>
          </tbody>
        </table>

        <hr />

        <!-- Recruiter Endpoints Section -->
        <h3 id="recruiter-endpoints">👔 Recruiter Endpoints</h3>
        <p><strong>Auth:</strong> Requires <code>ROLE_RECRUITER</code> token.</p>
        <table>
          <thead>
            <tr>
              <th>Method</th>
              <th>Endpoint</th>
              <th>Description</th>
            </tr>
          </thead>
          <tbody>
            <tr>
              <td><code>POST</code></td>
              <td><code>/api/jobs</code></td>
              <td>Creates a new job posting.</td>
            </tr>
            <tr>
              <td><code>GET</code></td>
              <td><code>/api/jobs/my-jobs</code></td>
              <td>Returns a list of all jobs posted by the user.</td>
            </tr>
            <tr>
              <td><code>PUT</code></td>
              <td><code>/api/jobs/{jobId}</code></td>
              <td>Updates the details of a job owned by the user.</td>
            </tr>
            <tr>
              <td><code>DELETE</code></td>
              <td><code>/api/jobs/{jobId}</code></td>
              <td>Deletes a job owned by the user.</td>
            </tr>
            <tr>
              <td><code>GET</code></td>
              <td><code>/api/jobs/{jobId}/applications</code></td>
              <td>Returns a list of all applications for a specific job.</td>
            </tr>
            <tr>
              <td><code>PUT</code></td>
              <td><code>/api/applications/{applicationId}/status</code></td>
              <td>Updates the status of an application.</td>
            </tr>
          </tbody>
        </table>

        <details>
          <summary><strong>JSON for <code>POST /api/jobs</code> or <code>PUT /api/jobs/{jobId}</code></strong></summary>
          <pre><code>{
  "title": "Senior Spring Boot Developer",
  "description": "Looking for a skilled developer with 5+ years of experience...",
  "companyName": "Tech Solutions Inc.",
  "location": "Remote"
}</code></pre>
        </details>

        <details>
          <summary><strong>JSON for <code>PUT /api/applications/{applicationId}/status</code></strong></summary>
          <pre><code>{
  "status": "UNDER_REVIEW"
}</code></pre>
          <small><em>Valid statuses: <code>APPLIED</code>, <code>VIEWED</code>, <code>UNDER_REVIEW</code>, <code>REJECTED</code>, <code>HIRED</code></em></small>
        </details>

        <hr />

        <!-- Admin Endpoints Section -->
        <h3 id="admin-endpoints">🛠️ Admin Endpoints</h3>
        <p><strong>Auth:</strong> Requires <code>ROLE_ADMIN</code> token.</p>
        <table>
          <thead>
            <tr>
              <th>Method</th>
              <th>Endpoint</th>
              <th>Description</th>
            </tr>
          </thead>
          <tbody>
            <tr>
              <td><code>GET</code></td>
              <td><code>/api/admin/users</code></td>
              <td>Returns a list of all users in the database.</td>
            </tr>
            <tr>
              <td><code>DELETE</code></td>
              <td><code>/api/admin/users/{userId}</code></td>
              <td>Deletes any user by their ID.</td>
            </tr>
            <tr>
              <td><code>DELETE</code></td>
              <td><code>/api/admin/jobs/{jobId}</code></td>
              <td>Deletes any job by its ID (for moderation).</td>
            </tr>
          </tbody>
        </table>
    </main>
</body>
</html>
