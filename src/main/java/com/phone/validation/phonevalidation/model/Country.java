package com.phone.validation.phonevalidation.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@Builder
@ToString
public class Country {

    String name;
    String code;
    String regex;
}
