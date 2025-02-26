package com.root.Crawler.services;

import com.root.Crawler.tasks.CrawlTask;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.Collections;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class CrawlerService {

    //thread-safe sorted for storing url's
    private final Set<String> collectedUrls = Collections.synchronizedSet(new TreeSet<>());
    private final ExecutorService executor = Executors.newFixedThreadPool(10);

    public Set<String> crawl(String baseUrl) {
        try {
            URI uri = new URI(baseUrl);
            String domain = uri.getHost();
            if (domain == null) {
                throw new IllegalArgumentException("Invalid base URL: " + baseUrl);
            }
            // keeping track of active tasks
            AtomicInteger counter = new AtomicInteger(0);
            counter.incrementAndGet();
            executor.submit(new CrawlTask(baseUrl, domain, collectedUrls, executor, counter));

            while (counter.get() > 0) {
                Thread.sleep(100);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            executor.shutdown();
        }
        return collectedUrls;
    }
}