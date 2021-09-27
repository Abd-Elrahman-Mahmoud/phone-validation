package com.phone.validation.phonevalidation.repositories;

import com.phone.validation.phonevalidation.model.Customer;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends PagingAndSortingRepository<Customer, Integer> {

    List<Customer> findAll();
}