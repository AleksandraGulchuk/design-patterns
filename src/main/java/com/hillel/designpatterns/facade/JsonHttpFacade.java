package com.hillel.designpatterns.facade;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hillel.designpatterns.facade.dto.UserRequest;
import com.hillel.designpatterns.facade.dto.UserResponse;
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


    public <T> T post(String url, UserRequest userRequest, Class<T> clazz) {
        try {
            String request = objectMapper.writeValueAsString(userRequest);
            HttpRequest httpRequest = HttpRequest.newBuilder()
                    .uri(URI.create(BASE_URL + url))
                    .POST(HttpRequest.BodyPublishers.ofString(request))
                    .header("Authorization", "Bearer " + getToken(userRequest))
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

    public <T> T authGet(String url, UserRequest userRequest, Class<T> clazz) {
        try {
            HttpRequest httpRequest = HttpRequest.newBuilder()
                    .uri(URI.create(BASE_URL + url))
                    .GET()
                    .header("Authorization", "Bearer " + getToken(userRequest))
                    .header("Accept", "application/json")
                    .build();
            HttpResponse<String> httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
            return objectMapper.readValue(httpResponse.body(), clazz);

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        throw new IllegalArgumentException("Something is wrong!");
    }

    private String getToken(UserRequest userRequest) {
        try {
            String request = objectMapper.writeValueAsString(userRequest);
            HttpRequest httpRequest = HttpRequest.newBuilder()
                    .uri(URI.create(BASE_URL + "/login"))
                    .POST(HttpRequest.BodyPublishers.ofString(request))
                    .header("Accept", "application/json")
                    .header("Content-Type", "application/json")
                    .build();
            HttpResponse<String> httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
            UserResponse userResponse = objectMapper.readValue(httpResponse.body(), UserResponse.class);
            return userResponse.getToken();

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        throw new IllegalArgumentException("Something is wrong!");
    }

}
