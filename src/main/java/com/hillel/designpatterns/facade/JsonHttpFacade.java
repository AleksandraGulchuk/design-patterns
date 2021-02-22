package com.hillel.designpatterns.facade;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@RequiredArgsConstructor
public class JsonHttpFacade {

    private final HttpClient httpClient;
    private final ObjectMapper objectMapper;
    private static final String BASE_URL = "https://mag-contacts-api.herokuapp.com";


    public <T> T post(String url, Object objectRequest, Class<T> clazz) {
        try {
            String request = objectMapper.writeValueAsString(objectRequest);
            HttpRequest httpRequest = HttpRequest.newBuilder()
                    .uri(URI.create(BASE_URL + url))
                    .POST(HttpRequest.BodyPublishers.ofString(request))
                    .header("Accept", "application/json")
                    .header("Content-Type", "application/json")
                    .build();
            HttpResponse<String> httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
            return objectMapper.readValue(httpResponse.body(), clazz);

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        throw new IllegalArgumentException("Something is wrong!");
    }

    public <T> T authPost(String url, String token, Object objectRequest, Class<T> clazz) {
        try {
            String request = objectMapper.writeValueAsString(objectRequest);
            HttpRequest httpRequest = HttpRequest.newBuilder()
                    .uri(URI.create(BASE_URL + url))
                    .POST(HttpRequest.BodyPublishers.ofString(request))
                    .header("Authorization", "Bearer " + token)
                    .header("Accept", "application/json")
                    .header("Content-Type", "application/json")
                    .build();
            HttpResponse<String> httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
            return objectMapper.readValue(httpResponse.body(), clazz);

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        throw new IllegalArgumentException("Something is wrong!");
    }


    public <T> T get(String url, Class<T> clazz) {
        try {
            HttpRequest httpRequest = HttpRequest.newBuilder()
                    .uri(URI.create(BASE_URL + url))
                    .GET()
                    .header("Accept", "application/json")
                    .build();
            HttpResponse<String> httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
            return objectMapper.readValue(httpResponse.body(), clazz);

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        throw new IllegalArgumentException("Something is wrong!");
    }

    public <T> T authGet(String url, String token, Class<T> clazz) {
        try {
            HttpRequest httpRequest = HttpRequest.newBuilder()
                    .uri(URI.create(BASE_URL + url))
                    .GET()
                    .header("Authorization", "Bearer " + token)
                    .header("Accept", "application/json")
                    .build();
            HttpResponse<String> httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
            return objectMapper.readValue(httpResponse.body(), clazz);

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        throw new IllegalArgumentException("Something is wrong!");
    }

}
