package com.phone.validation.phonevalidation.repositories;

import com.phone.validation.phonevalidation.model.Customer;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends PagingAndSortingRepository<Customer, Integer> {

}
