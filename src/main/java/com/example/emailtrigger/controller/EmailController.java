package com.example.emailtrigger.controller;

import com.example.emailtrigger.dto.ResponseStructure;
import com.example.emailtrigger.dto.SendEmailRequest;
import com.example.emailtrigger.dto.SendEmailResponse;
import com.example.emailtrigger.service.EmailService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/email")
public class EmailController {

    private final EmailService emailService;

    public EmailController(EmailService emailService) {
        this.emailService = emailService;
    }

    @Operation(summary = "Send email using template",
            description = "Triggers an email using an active email template and provided placeholders.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Email sent successfully",
                    content = @Content(schema = @Schema(implementation = SendEmailResponse.class))),
            @ApiResponse(responseCode = "500", description = "Failed to send email")
    })
    @PostMapping("/send")
    public ResponseEntity<ResponseStructure<SendEmailResponse>> send(@Valid @RequestBody SendEmailRequest request) {
        return emailService.sendEmail(request);
    }
}
