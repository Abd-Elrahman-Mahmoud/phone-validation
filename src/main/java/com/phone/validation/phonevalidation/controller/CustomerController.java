package com.phone.validation.phonevalidation.controller;

import com.phone.validation.phonevalidation.model.State;
import com.phone.validation.phonevalidation.model.dto.CustomerResponse;
import com.phone.validation.phonevalidation.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @GetMapping("/country/{name}")
    public List<CustomerResponse> filterByCountry(@PathVariable(name = "name") String name) {
        return customerService.filterByCountry(name);
    }

    @GetMapping("/state/{state}")
    public List<CustomerResponse> filterByState(@PathVariable(name = "state") State state) {
        return customerService.filterByState(state);
    }

    @GetMapping()
    public List<CustomerResponse> getAllCustomers() {
        return customerService.getAllCustomers();
    }
}
