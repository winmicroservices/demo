package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.model.Customer;
import com.example.demo.rest.repository.CustomerRepository;

@SpringBootTest
@Transactional
public class RepositoryTests {

    @Configuration
	@EnableAutoConfiguration
	static class Config {}

    private static Logger log = LoggerFactory.getLogger(RepositoryTests.class);

    @Autowired
    private CustomerRepository customerRepository;


    @Test
    public void testCustomerInsert() throws Exception {
        Customer customer = new Customer();
        customer.setName("Bill");
        customer.setCity("Venice");
        log.info("Saving customer {}",customer.getName());
        customerRepository.save(customer);
        Customer founEmployee = customerRepository.findByName("Bill");
        assertEquals(founEmployee.getName(), customer.getName());
    }

}