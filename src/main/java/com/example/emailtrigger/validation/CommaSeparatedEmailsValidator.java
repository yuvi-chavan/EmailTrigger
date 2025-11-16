package com.example.emailtrigger.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.mail.internet.InternetAddress;
import org.springframework.stereotype.Component;

@Component
public class CommaSeparatedEmailsValidator implements ConstraintValidator<CommaSeparatedEmails, String> {
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null || value.isBlank()) {
            return true; // allow null/blank (use @NotBlank on field if required)
        }
        String[] parts = value.split(",");
        for (String p : parts) {
            try {
                InternetAddress ia = new InternetAddress(p.trim());
                ia.validate();
            } catch (Exception e) {
                return false;
            }
        }
        return true;
    }
}
