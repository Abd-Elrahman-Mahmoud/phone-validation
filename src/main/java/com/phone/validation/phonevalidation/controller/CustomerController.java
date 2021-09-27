package com.phone.validation.phonevalidation.controller;

import com.phone.validation.phonevalidation.model.State;
import com.phone.validation.phonevalidation.model.dto.CustomerResponse;
import com.phone.validation.phonevalidation.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @GetMapping("/country/{name}")
    public Page<CustomerResponse> filterByCountry(@PathVariable(name = "name") String name, @PageableDefault(sort = {"id"}, direction = Sort.Direction.ASC, size = Integer.MAX_VALUE) Pageable pageable) {
        return customerService.filterByCountry(name, pageable);
    }

    @GetMapping("/state/{state}")
    public Page<CustomerResponse> filterByState(@PathVariable(name = "state") State state, @PageableDefault(sort = {"id"}, direction = Sort.Direction.ASC, size = Integer.MAX_VALUE) Pageable pageable) {
        return customerService.filterByState(state, pageable);
    }

    @GetMapping()
    public Page<CustomerResponse> getAllCustomers(@PageableDefault(sort = {"id"}, direction = Sort.Direction.ASC, size = Integer.MAX_VALUE) Pageable pageable) {
        return customerService.getAllCustomers(pageable);
    }
}
