package com.phone.validation.phonevalidation.model.dto;

import com.phone.validation.phonevalidation.model.State;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class CustomerResponse {

    private String name;
    private String phone;
    private String countryName;
    private State state;

}
