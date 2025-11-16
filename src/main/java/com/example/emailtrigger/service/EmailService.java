package com.example.emailtrigger.service;

import com.example.emailtrigger.dto.ResponseStructure;
import com.example.emailtrigger.dto.SendEmailRequest;
import com.example.emailtrigger.dto.SendEmailResponse;
import com.example.emailtrigger.entity.EmailTemplate;
import com.example.emailtrigger.entity.EmailLog;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private static final Logger logger = LoggerFactory.getLogger(EmailService.class);

    private final TemplateService templateService;
    private final TemplateRendererService renderer;
    private final MailService mailService;
    private final EmailLogService logService;

    @Value("${mail.default.from:yuvichavan968@gmail.com}")
    private String defaultFrom;

    public EmailService(TemplateService templateService,
                        TemplateRendererService renderer,
                        MailService mailService,
                        EmailLogService logService) {
        this.templateService = templateService;
        this.renderer = renderer;
        this.mailService = mailService;
        this.logService = logService;
    }

    public ResponseEntity<ResponseStructure<SendEmailResponse>> sendEmail(SendEmailRequest req) {
        logger.info("Initiating email send process for applicationId: {}", req.getApplicationId());
        EmailTemplate tpl = templateService.getActiveTemplate(req.getTemplateName());
        logger.info("Template fetched successfully: {}", tpl.getTemplateName());

        String from = tpl.getMailFrom() == null ? defaultFrom : tpl.getMailFrom();
        String subject = renderer.render(tpl.getEmailSubject(), req.getPlaceholders());
        String body = renderer.render(tpl.getTemplateBody(), req.getPlaceholders());

        try {
            mailService.send(from, req.getTo(), req.getCc(), subject, body, true);
            logger.info("Email sent successfully to: {}", req.getTo());

            EmailLog log = logService.createLog(
                    req.getApplicationId(), tpl.getTemplateName(), body, "SENT", req.getTo(), from
            );
            logger.info("Email log created with ID: {}", log.getUniqueId());

            SendEmailResponse response = new SendEmailResponse("SUCCESS", "Email sent successfully", log.getUniqueId());
            ResponseStructure<SendEmailResponse> structure = new ResponseStructure<>("SUCCESS", "Email sent successfully", response);
            return ResponseEntity.ok(structure);

        } catch (Exception e) {
            logger.error("Error while sending email: {}", e.getMessage(), e);
            EmailLog log = logService.createLog(
                    req.getApplicationId(), tpl.getTemplateName(), body, e.getMessage(), req.getTo(), from
            );

            SendEmailResponse response = new SendEmailResponse("FAILED", e.getMessage(), log.getUniqueId());
            ResponseStructure<SendEmailResponse> structure = new ResponseStructure<>("FAILED", e.getMessage(), response);
            return ResponseEntity.status(500).body(structure);
        }
    }
}
