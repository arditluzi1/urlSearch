package com.root.Crawler.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HtmlFetcher {

    public static String fetchContent(String urlString) {
        StringBuilder sb = new StringBuilder();
        try {
            URL urlObj = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) urlObj.openConnection();
            connection.setRequestProperty("User-Agent", "Mozilla/5.0");
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    sb.append(line);
                }
            }
        } catch (Exception e) {
            System.err.println("Error fetching " + urlString + ": " + e.getMessage());
        }
        return sb.toString();
    }
}

