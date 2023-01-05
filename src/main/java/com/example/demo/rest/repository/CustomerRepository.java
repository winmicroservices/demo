package com.example.demo.rest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.example.demo.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    public Customer findByFirstName(@Param("firstName") String firstName);
}
