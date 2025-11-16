package com.example.emailtrigger.exception;

public class TemplateNotFoundException extends RuntimeException {
    public TemplateNotFoundException(String templateName) {
        super("Template not found: " + templateName);
    }
}
