package com.phone.validation.phonevalidation.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
@ToString
@Entity
public class Customer {

    @Id
    @GeneratedValue
    int id;
    String name;
    String phone;
}
