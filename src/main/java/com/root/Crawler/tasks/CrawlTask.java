package com.root.Crawler.tasks;

import com.root.Crawler.utils.HtmlFetcher;
import com.root.Crawler.utils.HtmlParser;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.atomic.AtomicInteger;

public class CrawlTask implements Runnable {

    private final String url;
    private final String domain;
    private final Set<String> collectedUrls;
    private final ExecutorService executor;
    private final AtomicInteger counter;

    public CrawlTask(String url, String domain, Set<String> collectedUrls, ExecutorService executor, AtomicInteger counter) {
        this.url = url;
        this.domain = domain;
        this.collectedUrls = collectedUrls;
        this.executor = executor;
        this.counter = counter;
    }

    @Override
    public void run() {
        try {
            // skip if url was already procesed once.
            if (!collectedUrls.add(url)) {
                return;
            }
            String content = HtmlFetcher.fetchContent(url);
            Set<String> links = HtmlParser.parseLinks(content, url);
            for (String link : links) {
                try {
                    URL linkUrl = new URL(link);
                    if (linkUrl.getHost() != null && linkUrl.getHost().equalsIgnoreCase(domain)) {
                        counter.incrementAndGet();
                        executor.submit(new CrawlTask(link, domain, collectedUrls, executor, counter));
                    } else {
                        collectedUrls.add(link);
                    }
                } catch (MalformedURLException e) {
                }
            }
        } finally {
            counter.decrementAndGet();
        }
    }
}