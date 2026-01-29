package com.example.URL.Shortener.repository;


import com.example.URL.Shortener.model.ShortenRequest;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class InMemoryStorage {

    private final Map<String, String> urlToCode =new ConcurrentHashMap<>();
    private final Map<String, String> codeToUrl =new ConcurrentHashMap<>();

    public Map<String,String> getUrlToCode(){
        return urlToCode;
    }
    public Map<String,String> getCodeToUrl(){
        return codeToUrl;
    }


}
