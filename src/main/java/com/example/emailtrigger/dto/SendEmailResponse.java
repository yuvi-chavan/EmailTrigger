package com.example.emailtrigger.dto;


import lombok.Data;

@Data
public class SendEmailResponse {
    private String status;
    private String message;
    private Long logId;

    public SendEmailResponse(String status, String message, Long logId) {
        this.status = status;
        this.message = message;
        this.logId = logId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Long getLogId() {
        return logId;
    }

    public void setLogId(Long logId) {
        this.logId = logId;
    }

    @Override
    public String toString() {
        return "SendEmailResponse{" +
                "status='" + status + '\'' +
                ", message='" + message + '\'' +
                ", logId=" + logId +
                '}';
    }
}
