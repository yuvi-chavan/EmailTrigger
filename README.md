<p align="center">
  <img src="" width="420" />
</p>

<p align="center">
  <img src="https://img.shields.io/badge/Java-17-blue" />
  <img src="https://img.shields.io/badge/Spring%20Boot-3.x-brightgreen" />
  <img src="https://img.shields.io/badge/PostgreSQL-Database-lightblue" />
  <img src="https://img.shields.io/badge/Security-API%20Key-orange" />
  <img src="https://img.shields.io/badge/Build-Maven-yellow" />
  <img src="https://img.shields.io/badge/Project-Active-success" />
</p>

# 📌 Email Trigger API – Spring Boot + PostgreSQL + SMTP

A production-ready **Email Trigger Microservice** that sends emails using:

- ✅ Predefined templates  
- ✅ Dynamic placeholders  
- ✅ Multiple recipients (`to`)  
- ✅ CC support  
- ✅ API-Key based security  
- ✅ Email logging & auditing  
- ✅ PostgreSQL persistence  
- ✅ Centralized exception handling  

Built with **Spring Boot 3**, **PostgreSQL**, **JavaMailSender**, **JPA**, and a clean modular architecture.

---

## 📂 Project Structure

```text
src/main/java/com/example/emailtrigger/
│
├── controller
│      └── EmailController.java
│
├── dto
│      ├── ErrorResponse.java
│      ├── ResponseStructure.java
│      ├── SendEmailRequest.java
│      └── SendEmailResponse.java
│
├── entity
│      ├── EmailLog.java
│      └── EmailTemplate.java
│
├── exception
│      ├── GlobalExceptionHandler.java
│      ├── InvalidEmailException.java
│      ├── MailSendException.java
│      └── TemplateNotFoundException.java
│
├── repository
│      ├── EmailLogRepository.java
│      └── EmailTemplateRepository.java
│
├── security
│      ├── ApiKeyFilter.java
│      └── Config.java
│
├── service
│      ├── EmailLogService.java
│      ├── EmailService.java
│      ├── MailService.java
│      ├── TemplateRendererService.java
│      └── TemplateService.java
│
├── validation
│      ├── CommaSeparatedEmails.java
│      └── CommaSeparatedEmailsValidator.java
│
└── EmailTriggerApplication.java
🚀 Features
📧 Template-based emails (subject + body in DB)

🧩 Dynamic placeholders rendered inside templates

👥 Multiple to & cc recipients (comma-separated)

🔐 API Key-based authentication using a custom filter

📝 Email logs stored in PostgreSQL

⚠ Custom exceptions for invalid email / template not found / mail send failure

🧱 Layered architecture (Controller → Service → Repository)

✅ Validation for comma-separated email lists

🛠 Tech Stack
Java 17

Spring Boot 3

Spring Data JPA / Hibernate

PostgreSQL

JavaMailSender (SMTP)

Lombok

Maven

⚙️ Configuration (application.properties)
❗ Sensitive values should come from environment variables, not be hardcoded.

properties
Copy code
spring.application.name=EmailTrigger

# ========== Database ==========
spring.datasource.url=jdbc:postgresql://localhost:5432/emaildb
spring.datasource.username=${DB_USERNAME}
spring.datasource.password=${DB_PASSWORD}
spring.datasource.driver-class-name=org.postgresql.Driver

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true

# ========== SMTP Mail ==========
spring.mail.host=smtp.gmail.com
spring.mail.port=587
spring.mail.username=${MAIL_USERNAME}
spring.mail.password=${MAIL_PASSWORD}
spring.mail.properties.mail.smtp.auth=true
spring.mail.properties.mail.smtp.starttls.enable=true
spring.mail.properties.mail.smtp.starttls.required=true
spring.mail.properties.mail.smtp.connectiontimeout=5000
spring.mail.properties.mail.smtp.timeout=5000
spring.mail.properties.mail.smtp.writetimeout=5000

# ========== Application ==========
app.api-key=${API_KEY}
app.default-from=${MAIL_USERNAME}

# ========== Logging ==========
logging.level.root=INFO
logging.level.com.example.emailtrigger=DEBUG
logging.file.name=logs/email-trigger-api.log
logging.pattern.console=%d{yyyy-MM-dd HH:mm:ss} - %msg%n

server.port=8082
▶️ How to Run Locally
1️⃣ Clone the Repository
bash
Copy code
git clone https://github.com/yuvi-chavan/EmailTrigger.git
cd EmailTrigger
2️⃣ Create PostgreSQL Database
sql
Copy code
CREATE DATABASE emaildb;
3️⃣ Set Environment Variables
Windows PowerShell:

bash
Copy code
setx DB_USERNAME "postgres"
setx DB_PASSWORD "root"

setx MAIL_USERNAME "your@gmail.com"
setx MAIL_PASSWORD "your-app-password"

setx API_KEY "dev-key"
🔐 For Gmail, enable 2FA and create an App Password, then use it as MAIL_PASSWORD.

4️⃣ Run the Application
bash
Copy code
mvn spring-boot:run
App will be available at:
http://localhost:8082

📬 API – Send Email
Endpoint
POST /api/v1/email/send

Headers
Key	Value
Content-Type	application/json
X-API-KEY	dev-key

Request Body
json
Copy code
{
  "templateName": "DAILY_STANDUP_SUMMARY",
  "to": "user1@example.com,user2@example.com",
  "cc": "manager@example.com",
  "applicationId": 1024,
  "placeholders": {
    "userName": "John",
    "date": "08-Oct-2025",
    "summary": "Completed all tasks."
  }
}
templateName → Name of template stored in DB

to → comma-separated emails

cc → optional comma-separated emails

placeholders → key-value map used inside template body

✅ Success Response (200)
json
Copy code
{
  "status": "SUCCESS",
  "message": "Email sent successfully",
  "data": {
    "status": "SUCCESS",
    "message": "Email sent successfully",
    "logId": 1
  }
}
❌ Error Response (Example – Template Not Found)
json
Copy code
{
  "status": "ERROR",
  "message": "Email template not found"
}
Other error cases:

Invalid email format

SMTP failure

Missing API key / invalid API key

🧠 Internal Flow (High-Level)
text
Copy code
Client (Postman / Service)
        │
        │ 1. HTTP POST /api/v1/email/send
        │    with X-API-KEY + JSON body
        ▼
[ApiKeyFilter] → validates API key
        ▼
[EmailController] → validates request
        ▼
[TemplateService] → loads template from DB
        ▼
[TemplateRendererService] → replaces placeholders
        ▼
[MailService] → uses JavaMailSender to send email via SMTP
        ▼
[EmailLogService] → stores log in EmailLog table
        ▼
Response → SUCCESS / ERROR
🗄 Database ER Diagram (Text View)
text
Copy code
+------------------+        +------------------+
|  email_template  | 1    * |    email_logs    |
+------------------+        +------------------+
| id (PK)          |        | id (PK)          |
| template_name    |        | to_emails        |
| subject          |        | cc_emails        |
| body             |        | template_name    |
| created_at       |        | status           |
| updated_at       |        | error_message    |
+------------------+        | created_at       |
                            +------------------+
📸 Screenshots
You can add screenshots like:

Postman request/response

Logs file snippet

Database table entries

Example placeholder:

text
Copy code
assets/
  ├── postman-send-email.png
  └── email-log-table.png
Then in README:

md
Copy code
![Postman Example](assets/postman-send-email.png)
🧹 .gitignore (Recommended)
gitignore
Copy code
target/
logs/
.idea/
*.iml
.DS_Store
*.env
*.log
👨‍💻 Author
Yuvraj Chavan – Java Backend Developer
📧 Email: yuvichavan968@gmail.com
🔗 LinkedIn: https://www.linkedin.com/in/yuvrajchavan21
🐙 GitHub: https://github.com/yuvi-chavan

⭐ Support
If you find this project useful, please ⭐ star the repository on GitHub.
It helps others discover it and motivates further improvements!


