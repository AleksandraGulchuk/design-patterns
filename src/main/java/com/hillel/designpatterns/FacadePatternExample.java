package com.hillel.designpatterns;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hillel.designpatterns.facade.dto.*;
import com.hillel.designpatterns.facade.JsonHttpFacade;

import java.net.http.HttpClient;

public class FacadePatternExample {

    public static void createFacadeExample() {

        JsonHttpFacade jsonHttp = new JsonHttpFacade(HttpClient.newBuilder().build(), new ObjectMapper());

        UserResponse response = jsonHttp.get("/users", UserResponse.class);
        System.out.println(response);

        AuthRequest authRequest = new AuthRequest(
                "user0", "1234567890");
        StatusResponse authResponse = jsonHttp.post(
                "/login", authRequest, StatusResponse.class);
        System.out.println(authResponse);

        String token = authResponse.getToken();

        UserResponse responseAuthGet = jsonHttp.authGet(
                "/contacts", token, UserResponse.class);
        System.out.println(responseAuthGet);

        AddContactRequest contactRequest = new AddContactRequest(
                "email", "boo@hello.ua", "Boo"
        );
        StatusResponse addStatusResponse = jsonHttp.authPost(
                "/contacts/add", token, contactRequest, StatusResponse.class);
        System.out.println(addStatusResponse);

        SearchByValueContactRequest searchByValueContactRequest = new SearchByValueContactRequest("hello");
        StatusResponse searchByValueResponse = jsonHttp.authPost(
                "/contacts/find", token, searchByValueContactRequest, StatusResponse.class);
        System.out.println(searchByValueResponse);

        SearchByNameContactRequest searchByNameContactRequest = new SearchByNameContactRequest("V");
        StatusResponse searchByNameResponse = jsonHttp.authPost(
                "/contacts/find", token, searchByNameContactRequest, StatusResponse.class);
        System.out.println(searchByNameResponse);

    }

}
