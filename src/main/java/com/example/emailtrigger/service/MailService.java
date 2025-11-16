package com.example.emailtrigger.service;

import com.example.emailtrigger.exception.MailSendException;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class MailService {

    private static final Logger logger = LoggerFactory.getLogger(MailService.class);
    private final JavaMailSender mailSender;

    public MailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void send(String from, String toCsv, String ccCsv, String subject, String body, boolean isHtml) {
        logger.info("Preparing email to: {} with subject: {}", toCsv, subject);
        try {
            MimeMessage msg = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(msg, false, "UTF-8");
            helper.setFrom(from);
            helper.setTo(splitAndTrim(toCsv));
            if (ccCsv != null && !ccCsv.isBlank()) helper.setCc(splitAndTrim(ccCsv));
            helper.setSubject(subject);
            helper.setText(body, isHtml);

            mailSender.send(msg);
            logger.info("Email sent successfully to: {}", toCsv);

        } catch (MessagingException e) {
            logger.error("Messaging error while sending mail: {}", e.getMessage(), e);
            throw new MailSendException("Failed to send mail: " + e.getMessage(), e);
        } catch (Exception e) {
            logger.error("Unexpected error while sending mail: {}", e.getMessage(), e);
            throw new MailSendException("Failed to send mail: " + e.getMessage(), e);
        }
    }

    private String[] splitAndTrim(String csv) {
        return java.util.Arrays.stream(csv.split(","))
                .map(String::trim)
                .filter(s -> !s.isBlank())
                .toArray(String[]::new);
    }
}

