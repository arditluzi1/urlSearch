package com.root.Crawler;


import com.root.Crawler.services.CrawlerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Set;

@SpringBootApplication
public class CrawlerMainClass implements CommandLineRunner {

	@Autowired
	private CrawlerService crawlerService;

	public static void main(String[] args) {
		SpringApplication.run(CrawlerMainClass.class, args);
	}

	@Override
	public void run(String... args) {
		//provide your domain egg: https://www.ecosio.com
		String baseUrl = args.length > 0 ? args[0] : "https://www.ecosio.com";
		Set<String> collectedUrls = crawlerService.crawl(baseUrl);
		System.out.println("Collected URLs:");
		collectedUrls.forEach(System.out::println);
	}
}
