package com.example.URL.Shortener.service;


import com.example.URL.Shortener.model.ShortenRequest;
import com.example.URL.Shortener.repository.InMemoryStorage;
import com.example.URL.Shortener.util.HashUtil;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.net.URI;

@Service
public class UrlShortenerService{

    private final InMemoryStorage repo;

    public UrlShortenerService(InMemoryStorage repo){
        this.repo =repo;
    }
    public String shortenUrl(String url){
        return repo.getUrlToCode().computeIfAbsent(url, u ->{
            String code =HashUtil.generateShortCode(u);
            repo.getCodeToUrl().put(code, u);
            System.out.println(repo.getUrlToCode());
            System.out.println(repo.getCodeToUrl());
            return code;
        });
    }

}
