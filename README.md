URL Shortener – Spring Boot

A simple URL Shortener service built with Java 17 Spring Boot.

This project allows users to:

  1.Shorten URLs via REST API

  2.Redirect short URLs to the original URL

  3.View metrics: top 3 most frequently shortened domains

  4.Data is stored in-memory (no database required).


REST APIs-

1️⃣Shorten API-
http://localhost:8080/shorten
RequestBody-
{
  "url": "https://google.com"
}
Response-http://localhost:8080/0OGWoM

Success	200	- "http://localhost:8080/u/abc123"
Invalid input -	400	"Invalid URL"


2️⃣Redirect API-
http://localhost:8080/{code}
Instead of "{code}" mentioned the shorten code and It will redirect to the original url

3️⃣ Top Domains Metrics-
http://localhost:8080/metrics
Success 200- 
{
    "www.google.com": 3,
    "www.youtube.com": 2,
    "github.com": 1
}
When no record is there status code 404- "No record found"


Added the docker file-
Docker url- https://hub.docker.com/r/faiyaz1505/url-shortener
