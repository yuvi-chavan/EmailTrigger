package com.example.emailtrigger.repository;



import com.example.emailtrigger.entity.EmailTemplate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmailTemplateRepository extends JpaRepository<EmailTemplate, Integer> {
    Optional<EmailTemplate> findByTemplateNameAndIsActiveTrue(String templateName);
}