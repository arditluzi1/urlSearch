# UrlSearchApplication

## Description

UrlSearchApplication is a simple multithreaded web crawler built with Spring Boot and Java. 
The application recursively visits pages on a specified website, collects all URLs found, and outputs a sorted list of URLs once the crawl is complete.

Features:
- **Recursive Crawling:** For each internal link found, the crawler "starts over" by fetching, parsing, and scheduling further crawl tasks.
- **Multithreading:** Uses an ExecutorService with a fixed thread pool and an AtomicInteger to manage concurrent crawling tasks efficiently.
- **Modular Design:** The code is split into several classes (CrawlerService, CrawlTask, HtmlFetcher, and HtmlParser) for clear separation of concerns.

## Prerequisites

- Java 17 or higher
- Gradle 3.8 or higher

## How to Build
This is a gradle based project, you can build it through comand line or import it in you IDE (intelliJ in my end) as a SpringBoot App, 
provide the URL in the main class, and check the console where you will receive printed out of list of URL's. (by default "wwww.ecosio.com" is specified.
