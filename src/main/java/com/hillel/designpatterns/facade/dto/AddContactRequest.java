package com.hillel.designpatterns.facade.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddContactRequest {

    private String type;
    private String value;
    private String name;


}
