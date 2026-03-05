package com.sachin.urlshortener.service;

import java.util.Optional;
import org.springframework.stereotype.Service;

import com.sachin.urlshortener.model.UrlMapping;
import com.sachin.urlshortener.repository.UrlMappingRepository;
import com.sachin.urlshortener.util.Base62Encoder;

@Service
public class UrlShortenerService {

    private final UrlMappingRepository repository;

    public UrlShortenerService(UrlMappingRepository repository) {
        this.repository = repository;
    }

    public String shortenUrl(String originalUrl) {

        // 1️⃣ Check if already exists
        Optional<UrlMapping> existing = repository.findByOriginalUrl(originalUrl);
        if (existing.isPresent()) {
            return existing.get().getShortCode();
        }

        // 2️⃣ Create new mapping (without shortCode)
        UrlMapping mapping = new UrlMapping();
        mapping.setOriginalUrl(originalUrl);

        // 3️⃣ Save once to generate ID
        UrlMapping saved = repository.save(mapping);

        // 4️⃣ Generate Base62 short code from ID
        String code = Base62Encoder.encode(saved.getId());
        saved.setShortCode(code);

        // 5️⃣ Save again (update)
        repository.save(saved);

        return code;
    }

    public String getOriginalUrl(String shortCode) {
        return repository.findByShortCode(shortCode)
                .map(UrlMapping::getOriginalUrl)
                .orElseThrow(() -> new RuntimeException("Short URL not found"));
    }
}