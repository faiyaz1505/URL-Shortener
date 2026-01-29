package com.example.URL.Shortener;

import com.example.URL.Shortener.service.UrlService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@SpringBootTest
class UrlShortenerApplicationTests {


	@Autowired
	UrlService service;


	@Test
	void sameUrlReturnsSameShortCode() {
		String url = service.shortenUrl("https://google.com");
		String url2 = service.shortenUrl("https://google.com");

		assertEquals(url, url2);
	}


	@Test
	void differentUrlReturnsDifferentShortCode() {
		String url = service.shortenUrl("https://google.com");
		String url2 = service.shortenUrl("https://youtube.com");
		assertNotEquals(url,url2);
	}

	@Test
	void domainCountShouldIncreaseWhenUrlIsShortened() throws Exception {
		service.shortenUrl("https://yahoo.com/search");
		service.shortenUrl("https://yahoo.com/maps");
		service.shortenUrl("https://github.com/watch");

		Map<String, Integer> topDomains = service.topDomains();

		assertEquals(2, topDomains.get("yahoo.com"));
		assertEquals(1, topDomains.get("github.com"));
	}
}
