package com.phone.validation.phonevalidation.service;

import com.phone.validation.phonevalidation.configurations.ContriesLoading;
import com.phone.validation.phonevalidation.model.Country;
import com.phone.validation.phonevalidation.model.Customer;
import com.phone.validation.phonevalidation.model.State;
import com.phone.validation.phonevalidation.model.dto.CustomerResponse;
import com.phone.validation.phonevalidation.model.mapper.CustomerMapper;
import com.phone.validation.phonevalidation.repositories.CustomerRepository;
import com.phone.validation.phonevalidation.util.DataUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    CustomerMapper customerMapper;

    public Page<CustomerResponse> filterByCountry(String name, Pageable pageable) {

        Page<CustomerResponse> customersResultList = new PageImpl<>(new ArrayList<>());
        Optional<Country> matchedCountry = DataUtil.safeStream(ContriesLoading.countries).filter(country -> country.getName().equals(name)).findFirst();
        if (matchedCountry.isPresent()) {
            List<Customer> customers = customerRepository.findAll();
            customers = DataUtil.safeStream(customers).filter(customer -> customer.getPhone().matches(matchedCountry.get().getRegex())).collect(Collectors.toList());
            customersResultList = customerMapper.customersToCustomersResponse(customers, matchedCountry.get().getName(), pageable);
        }
        return customersResultList;
    }

    public Page<CustomerResponse> filterByState(State state, Pageable pageable) {

        List<CustomerResponse> customersResultList = new ArrayList<>();
        List<Customer> customers = customerRepository.findAll();
        customers.forEach(customer -> {
            Optional<Country> matchedCountry = DataUtil.safeStream(ContriesLoading.countries).filter(country -> customer.getPhone().matches(country.getRegex())).findFirst();
            if (state.equals(State.VALID) && matchedCountry.isPresent()) {
                CustomerResponse customerResponse = customerMapper.customerToCustomerResponse(customer, matchedCountry.get().getName());
                customersResultList.add(customerResponse);
            } else if (state.equals(State.INVALID) && matchedCountry.isEmpty()) {
                CustomerResponse customerResponse = customerMapper.customerToCustomerResponse(customer, "");
                customersResultList.add(customerResponse);
            }
        });
        return customerMapper.customersListToCustomersResponseList(customersResultList, pageable);
    }

    public Page<CustomerResponse> getAllCustomers(Pageable pageable) {
        List<CustomerResponse> customersResultList = new ArrayList<>();
        Page<Customer> customers = customerRepository.findAll(pageable);
        customers.forEach(customer -> {
            Optional<Country> matchedCountry = DataUtil.safeStream(ContriesLoading.countries).filter(country -> customer.getPhone().matches(country.getRegex())).findFirst();
            CustomerResponse customerResponse;
            String countryName = (matchedCountry.isPresent() ? matchedCountry.get().getName() : "");
            customerResponse = customerMapper.customerToCustomerResponse(customer, countryName);
            customersResultList.add(customerResponse);
        });
        return new PageImpl<>(customersResultList, pageable, customersResultList.size());
    }
}
