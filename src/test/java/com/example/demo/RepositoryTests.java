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

import com.example.demo.model.Employee;
import com.example.demo.rest.repository.EmployeeRepository;

@SpringBootTest
@Transactional
public class RepositoryTests {

    @Configuration
	@EnableAutoConfiguration
	static class Config {}

    private static Logger log = LoggerFactory.getLogger(RepositoryTests.class);

    @Autowired
    private EmployeeRepository employeeRepository;


    @Test
    public void testEmployeeInsert() throws Exception {
        Employee employee = new Employee();
        employee.setName("Bill");
        employee.setCity("Venice");
        employeeRepository.save(employee);
        Employee founEmployee = employeeRepository.findByName("Bill");
        assertEquals(founEmployee.getName(), employee.getName());
    }

}