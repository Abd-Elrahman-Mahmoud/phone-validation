package com.phone.validation.phonevalidation.model.mapper;

import com.phone.validation.phonevalidation.model.Customer;
import com.phone.validation.phonevalidation.model.State;
import com.phone.validation.phonevalidation.model.dto.CustomerResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface CustomerMapper {


    default List<CustomerResponse> customersToCustomersResponse(List<Customer> customers, String countryName) {
        return customers.stream().map(customer -> customerToCustomerResponse(customer, countryName)).
                collect(Collectors.toList());
    }

    @Mapping(target = "countryName", source = "countryName")
    @Mapping(target = "state", source = "countryName", qualifiedByName = "getSate")
    CustomerResponse customerToCustomerResponse(Customer customer, String countryName);

    @Named("getSate")
    default State getSate(String countryName) {
        return (countryName == null || countryName.isBlank() ? State.INVALID : State.VALID);
    }

}
