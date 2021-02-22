package com.hillel.designpatterns.facade.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class StatusResponse {

    private String status;
    private String error;
    private String token;
}
