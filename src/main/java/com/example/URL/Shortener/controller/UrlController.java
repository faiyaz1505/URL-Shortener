package com.example.URL.Shortener.controller;


import com.example.URL.Shortener.model.ShortenRequest;
import com.example.URL.Shortener.service.UrlShortenerService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UrlController {

    private final UrlShortenerService urlService;

    public UrlController(UrlShortenerService urlService) {
        this.urlService = urlService;
    }

    @PostMapping("/shorten")
    public String shorten(@RequestBody ShortenRequest request) {
        String code = urlService.shortenUrl(request.getUrl());
        return "http://localhost:8080/" + code;
    }
}
