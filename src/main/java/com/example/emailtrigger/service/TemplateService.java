package com.example.emailtrigger.service;

import com.example.emailtrigger.entity.EmailTemplate;
import com.example.emailtrigger.exception.TemplateNotFoundException;
import com.example.emailtrigger.repository.EmailTemplateRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class TemplateService {

    private static final Logger logger = LoggerFactory.getLogger(TemplateService.class);
    private final EmailTemplateRepository repository;

    public TemplateService(EmailTemplateRepository repository) {
        this.repository = repository;
    }

    public EmailTemplate getActiveTemplate(String templateName) {
        logger.info("Fetching active template: {}", templateName);
        return repository.findByTemplateNameAndIsActiveTrue(templateName)
                .orElseThrow(() -> {
                    logger.error("Template not found: {}", templateName);
                    return new TemplateNotFoundException(templateName);
                });
    }
}



