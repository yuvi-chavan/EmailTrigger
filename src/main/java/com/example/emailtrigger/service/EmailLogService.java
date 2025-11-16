package com.example.emailtrigger.service;

import com.example.emailtrigger.entity.EmailLog;
import com.example.emailtrigger.repository.EmailLogRepository;
import com.example.emailtrigger.security.Config;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class EmailLogService {

    private static final Logger logger = LoggerFactory.getLogger(EmailLogService.class);
    private final EmailLogRepository repository;
    private final Config config;

    public EmailLogService(EmailLogRepository repository, Config config) {
        this.repository = repository;
        this.config = config;
    }

    public EmailLog createLog(Integer applicationId, String templateName, String body,
                              String response, String to, String from) {
        logger.info("Creating email log for template: {}", templateName);
        EmailLog log = new EmailLog();
        log.setApplicationId(applicationId);
        log.setEmailTemplate(templateName);
        log.setEmailBody(body);
        log.setResponse(response);
        log.setCreatedBy(config.getCurrentUsername());
        log.setEmailTo(to);
        log.setEmailFrom(from);
        log.setUpdatedBy(config.getCurrentUsername());
        log.setLastUpdatedOn(LocalDateTime.now());
        EmailLog saved = repository.save(log);
        logger.info("Email log saved with ID: {}", saved.getUniqueId());
        return saved;
    }
}



