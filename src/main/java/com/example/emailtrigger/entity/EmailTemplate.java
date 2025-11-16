package com.example.emailtrigger.entity;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "email_template")
@Data
public class EmailTemplate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "template_id")
    private Integer templateId;

    @Column(name = "template_name", nullable = false, unique = true)
    private String templateName;

    @Column(name = "template_body", columnDefinition = "text", nullable = false)
    private String templateBody;

    @Column(name = "is_active")
    private Boolean isActive;

    @Column(name = "mail_to")
    private String mailTo;

    @Column(name = "mail_from")
    private String mailFrom;

    @Column(name = "mail_cc")
    private String mailCc;

    @Column(name = "status")
    private String status;

    @Column(name = "email_from_password")
    private String emailFromPassword;

    @Column(name = "email_subject")
    private String emailSubject;

    public Integer getTemplateId() {
        return templateId;
    }

    public void setTemplateId(Integer templateId) {
        this.templateId = templateId;
    }

    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }

    public String getTemplateBody() {
        return templateBody;
    }

    public void setTemplateBody(String templateBody) {
        this.templateBody = templateBody;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public String getMailTo() {
        return mailTo;
    }

    public void setMailTo(String mailTo) {
        this.mailTo = mailTo;
    }

    public String getMailFrom() {
        return mailFrom;
    }

    public void setMailFrom(String mailFrom) {
        this.mailFrom = mailFrom;
    }

    public String getMailCc() {
        return mailCc;
    }

    public void setMailCc(String mailCc) {
        this.mailCc = mailCc;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getEmailFromPassword() {
        return emailFromPassword;
    }

    public void setEmailFromPassword(String emailFromPassword) {
        this.emailFromPassword = emailFromPassword;
    }

    public String getEmailSubject() {
        return emailSubject;
    }

    public void setEmailSubject(String emailSubject) {
        this.emailSubject = emailSubject;
    }

    @Override
    public String toString() {
        return "EmailTemplate{" +
                "templateId=" + templateId +
                ", templateName='" + templateName + '\'' +
                ", templateBody='" + templateBody + '\'' +
                ", isActive=" + isActive +
                ", mailTo='" + mailTo + '\'' +
                ", mailFrom='" + mailFrom + '\'' +
                ", mailCc='" + mailCc + '\'' +
                ", status='" + status + '\'' +
                ", emailFromPassword='" + emailFromPassword + '\'' +
                ", emailSubject='" + emailSubject + '\'' +
                '}';
    }
}

