package com.hillel.designpatterns.facade.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {

    private String status;
    private List<Object> users;
    private List<Object> contacts;
    private String error;
    private String token;

}
