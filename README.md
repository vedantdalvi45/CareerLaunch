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
<table class="api-table">
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
<table class="api-table">
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
<table class="api-table">
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
<table class="api-table">
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
<table class="api-table">
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

<!-- Basic Styling (Optional) -->
<style>
  body {
    font-family: -apple-system, BlinkMacSystemFont, "Segoe UI", Roboto, "Helvetica Neue", Arial, sans-serif;
    line-height: 1.6;
    color: #333;
    padding: 20px;
  }
  h2, h3 {
    border-bottom: 2px solid #eaecef;
    padding-bottom: 0.3em;
  }
  code {
    font-family: "SFMono-Regular", Consolas, "Liberation Mono", Menlo, monospace;
    background-color: #f6f8fa;
    padding: 0.2em 0.4em;
    margin: 0;
    border-radius: 6px;
  }
  pre {
    background-color: #f6f8fa;
    padding: 16px;
    overflow: auto;
    border-radius: 6px;
  }
  pre code {
    padding: 0;
    background-color: transparent;
  }
  .api-table {
    border-collapse: collapse;
    width: 100%;
    margin: 1em 0;
  }
  .api-table th, .api-table td {
    border: 1px solid #dfe2e5;
    padding: 8px 12px;
  }
  .api-table th {
    background-color: #f6f8fa;
    font-weight: 600;
  }
  .api-table td:first-child code {
    font-weight: 600;
    color: #005cc5;
  }
  details {
    margin: 1em 0;
    border: 1px solid #dfe2e5;
    border-radius: 6px;
  }
  details[open] {
    border-bottom: 1px solid #dfe2e5;
  }
  summary {
    padding: 10px 16px;
    cursor: pointer;
    background-color: #f6f8fa;
    border-radius: 6px;
  }
  details[open] summary {
     border-bottom: 1px solid #dfe2e5;
     border-bottom-left-radius: 0;
     border-bottom-right-radius: 0;
  }
  details pre {
    margin: 0;
    border-top-left-radius: 0;
    border-top-right-radius: 0;
  }
</style>
