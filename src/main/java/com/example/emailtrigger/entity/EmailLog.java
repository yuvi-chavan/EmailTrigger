package com.example.emailtrigger.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;


@Entity
@Table(name = "email_logs")
@Data
public class EmailLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long uniqueId;


    private Integer applicationId;
    private String emailTemplate;
    @Column(columnDefinition = "text")
    private String emailBody;
    @Column(columnDefinition = "text")
    private String response;
    private String emailTo;
    private String emailFrom;
    private LocalDateTime executeTime;
    private String createdBy;
    private LocalDateTime createdOn;
    private String updatedBy;
    private LocalDateTime lastUpdatedOn;

    public Long getUniqueId() {
        return uniqueId;
    }

    public void setUniqueId(Long uniqueId) {
        this.uniqueId = uniqueId;
    }

    public Integer getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(Integer applicationId) {
        this.applicationId = applicationId;
    }

    public String getEmailTemplate() {
        return emailTemplate;
    }

    public void setEmailTemplate(String emailTemplate) {
        this.emailTemplate = emailTemplate;
    }

    public String getEmailBody() {
        return emailBody;
    }

    public void setEmailBody(String emailBody) {
        this.emailBody = emailBody;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public String getEmailTo() {
        return emailTo;
    }

    public void setEmailTo(String emailTo) {
        this.emailTo = emailTo;
    }

    public String getEmailFrom() {
        return emailFrom;
    }

    public void setEmailFrom(String emailFrom) {
        this.emailFrom = emailFrom;
    }

    public LocalDateTime getExecuteTime() {
        return executeTime;
    }

    public void setExecuteTime(LocalDateTime executeTime) {
        this.executeTime = executeTime;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public LocalDateTime getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(LocalDateTime createdOn) {
        this.createdOn = createdOn;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public LocalDateTime getLastUpdatedOn() {
        return lastUpdatedOn;
    }

    public void setLastUpdatedOn(LocalDateTime lastUpdatedOn) {
        this.lastUpdatedOn = lastUpdatedOn;
    }


    @Override
    public String toString() {
        return "EmailLog{" +
                "uniqueId=" + uniqueId +
                ", applicationId=" + applicationId +
                ", emailTemplate='" + emailTemplate + '\'' +
                ", emailBody='" + emailBody + '\'' +
                ", response='" + response + '\'' +
                ", emailTo='" + emailTo + '\'' +
                ", emailFrom='" + emailFrom + '\'' +
                ", executeTime=" + executeTime +
                ", createdBy='" + createdBy + '\'' +
                ", createdOn=" + createdOn +
                ", updatedBy='" + updatedBy + '\'' +
                ", lastUpdatedOn=" + lastUpdatedOn +
                '}';
    }

    @PrePersist
    public void prePersist() {
        this.createdOn = java.time.LocalDateTime.now();
        this.executeTime = this.createdOn;
    }


    @PreUpdate
    public void preUpdate() {
        this.lastUpdatedOn = java.time.LocalDateTime.now();
    }


// getters and setters
}

