package com.root.Crawler.utils;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Set;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HtmlParser {

    public static Set<String> parseLinks(String html, String baseUrl) {
        Set<String> links = new TreeSet<>();
        // regex to capture href attribute.
        Pattern pattern = Pattern.compile("href\\s*=\\s*[\"']([^\"']+)[\"']");
        Matcher matcher = pattern.matcher(html);
        while (matcher.find()) {
            String link = matcher.group(1);
            // ignore javascript and fragment links.
            if (link.startsWith("javascript:") || link.startsWith("#")) {
                continue;
            }
            try {
                URL absoluteUrl = new URL(new URL(baseUrl), link);
                links.add(absoluteUrl.toString());
            } catch (MalformedURLException e) {
                // skip invalid ones
            }
        }
        return links;
    }
}