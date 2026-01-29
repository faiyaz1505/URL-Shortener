package com.example.URL.Shortener.service;


import com.example.URL.Shortener.repository.InMemoryStorage;
import com.example.URL.Shortener.util.HashUtil;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class UrlService {

    private final InMemoryStorage repo;

    public UrlService(InMemoryStorage repo){
        this.repo =repo;
    }
    public String shortenUrl(String url){
        if(!isValidUrl(url)){
            throw new IllegalArgumentException("Invalid URL");
        }
        return repo.getUrlToCode().computeIfAbsent(url, u ->{
            String code =HashUtil.generateShortCode(u);
            repo.getCodeToUrl().put(code, u);
            String domain = URI.create(u).getHost();
            repo.getDomainCount().merge(domain, 1, Integer::sum);//storing the domain with count to get metrics
            return code;
        });
    }

    private boolean isValidUrl(String url) {
        URI uri=URI.create(url);
        return (uri.getScheme()!=null) && (uri.getScheme().equalsIgnoreCase("http")|| uri.getScheme().equalsIgnoreCase("https")) && uri.getHost()!=null;
    }


    public String getOriginalUrl(String code) {
        String url=repo.getCodeToUrl().get(code);
        if(url==null){
            throw new RuntimeException("No Record found");
        }
        return url;
    }

    public Map<String, Integer> topDomains() {
        return repo.getDomainCount().entrySet().stream()
                .sorted((a, b) -> b.getValue().compareTo(a.getValue())).limit(3)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                        (a, b) -> a,
                        LinkedHashMap::new
                ));
    }

}
