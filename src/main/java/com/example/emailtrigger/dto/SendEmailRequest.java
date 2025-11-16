package com.example.emailtrigger.dto;

import com.example.emailtrigger.validation.CommaSeparatedEmails;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SendEmailRequest {
    @NotBlank
    private String templateName;

    @NotBlank
    @CommaSeparatedEmails
    private String to;

    @CommaSeparatedEmails
    private String cc;

    private Integer applicationId;

    private Map<String, String> placeholders;

    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getCc() {
        return cc;
    }

    public void setCc(String cc) {
        this.cc = cc;
    }

    public Integer getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(Integer applicationId) {
        this.applicationId = applicationId;
    }

    public Map<String, String> getPlaceholders() {
        return placeholders;
    }

    public void setPlaceholders(Map<String, String> placeholders) {
        this.placeholders = placeholders;
    }

    @Override
    public String toString() {
        return "SendEmailRequest{" +
                "templateName='" + templateName + '\'' +
                ", to='" + to + '\'' +
                ", cc='" + cc + '\'' +
                ", applicationId=" + applicationId +
                ", placeholders=" + placeholders +
                '}';
    }
}

