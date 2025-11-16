package com.example.emailtrigger.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Constraint(validatedBy = CommaSeparatedEmailsValidator.class)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CommaSeparatedEmails {
    String message() default "Invalid email list";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}