package com.phone.validation.phonevalidation.model.mapper;

import com.phone.validation.phonevalidation.model.Customer;
import com.phone.validation.phonevalidation.model.State;
import com.phone.validation.phonevalidation.model.dto.CustomerResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CustomerMapper {


    default Page<CustomerResponse> customersToCustomersResponse(List<Customer> customers, String countryName, Pageable pageable) {
        final int start = Math.min((int) pageable.getOffset(), customers.size());
        final int end = Math.min((start + pageable.getPageSize()), customers.size());
        final Page<Customer> page = new PageImpl<>(customers.subList(start, end), pageable, customers.size());
        return page.map(customer -> customerToCustomerResponse(customer, countryName));
    }

    @Mapping(target = "countryName", source = "countryName")
    @Mapping(target = "state", source = "countryName", qualifiedByName = "getSate")
    CustomerResponse customerToCustomerResponse(Customer customer, String countryName);

    @Named("getSate")
    default State getSate(String countryName) {
        return (countryName == null || countryName.isBlank() ? State.INVALID : State.VALID);
    }

    default Page<CustomerResponse> customersListToCustomersResponseList(List<CustomerResponse> customers, Pageable pageable) {
        final int start = Math.min((int) pageable.getOffset(), customers.size());
        final int end = Math.min((start + pageable.getPageSize()), customers.size());
        return new PageImpl<>(customers.subList(start, end), pageable, customers.size());
    }

}
