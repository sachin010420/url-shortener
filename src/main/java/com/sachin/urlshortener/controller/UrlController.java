package com.sachin.urlshortener.controller;

import org.springframework.web.bind.annotation.*;
import com.sachin.urlshortener.service.UrlShortenerService;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping("/api")
public class UrlController {

    private final UrlShortenerService service;

    public UrlController(UrlShortenerService service) {
        this.service = service;
    }

    @PostMapping("/shorten")
    public String shorten(@RequestParam String url) {
        return service.shortenUrl(url);
    }
    
    @GetMapping("/{shortCode}")
public void redirect(@PathVariable String shortCode,
                     HttpServletResponse response) throws IOException {
    String originalUrl = service.getOriginalUrl(shortCode);
    response.sendRedirect(originalUrl);
}
}
