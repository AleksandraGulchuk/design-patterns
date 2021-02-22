package com.hillel.designpatterns;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hillel.designpatterns.facade.dto.UserRequest;
import com.hillel.designpatterns.facade.JsonHttpFacade;
import com.hillel.designpatterns.facade.dto.StatusResponse;
import com.hillel.designpatterns.facade.dto.UserResponse;

import java.net.http.HttpClient;

public class FacadePatternExample {

    public static void createFacadeExample() {

        JsonHttpFacade jsonHttp = new JsonHttpFacade(HttpClient.newBuilder().build(), new ObjectMapper());

        UserResponse response = jsonHttp.get("/users", UserResponse.class);
        System.out.println(response);

        UserRequest userRequest = new UserRequest(
                "user0",
                "1234567890");
        StatusResponse authResponse = jsonHttp.post(
                //  "/register",
                "/login",
                userRequest,
                StatusResponse.class
        );

        System.out.println(authResponse);

        UserResponse authResponseGet = jsonHttp.authGet(
                // "/contacts",
                "/users2",
                userRequest,
                UserResponse.class
        );

        System.out.println(authResponseGet);
    }


}
