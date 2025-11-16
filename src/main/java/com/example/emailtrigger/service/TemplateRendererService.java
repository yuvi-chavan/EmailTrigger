package com.example.emailtrigger.service;

import com.github.mustachejava.DefaultMustacheFactory;
import com.github.mustachejava.Mustache;
import com.github.mustachejava.MustacheFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.StringReader;
import java.io.StringWriter;
import java.util.Map;

@Service
public class TemplateRendererService {

    private static final Logger logger = LoggerFactory.getLogger(TemplateRendererService.class);
    private final MustacheFactory mf = new DefaultMustacheFactory();

    public String render(String template, Map<String, String> placeholders) {
        logger.info("Rendering template with placeholders: {}", placeholders != null ? placeholders.keySet() : "none");
        if (template == null) return "";
        Mustache m = mf.compile(new StringReader(template), "template");
        StringWriter out = new StringWriter();
        m.execute(out, placeholders == null ? Map.of() : placeholders);
        return out.toString();
    }
}

