package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Employee;
import com.example.demo.rest.repository.EmployeeRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/api")
public class EmployeeController {

    private final static Logger log = LoggerFactory.getLogger(EmployeeController.class);

    @Autowired
    EmployeeRepository employeeRepository;

    @GetMapping("/employee/{id}")
    public Employee getEmployee(@PathVariable long id) {
        Employee employee = employeeRepository.getReferenceById(id);
        return employee;
    }

    @PostMapping("/saveemployee/{name}/{city}")
    public void saveEmployee(@PathVariable String name, @PathVariable String city) {
        log.info("Saving employee %s",name);
        Employee employee = new Employee();
        employee.setName(name);
        employee.setCity(city);
        employeeRepository.save(employee);
    }
}