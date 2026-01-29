package com.example.URL.Shortener.service;


import com.example.URL.Shortener.repository.InMemoryStorage;
import com.example.URL.Shortener.util.HashUtil;
import org.springframework.stereotype.Service;

@Service
public class UrlService {

    private final InMemoryStorage repo;

    public UrlService(InMemoryStorage repo){
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


    public String getOriginalUrl(String code) {
        String url=repo.getCodeToUrl().get(code);
        if(url==null){
            throw new RuntimeException("No Record found");
        }
        return url;
    }

}
