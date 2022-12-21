package com.example.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Employee;
import com.example.demo.rest.repository.EmployeeRepository;

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

    @RequestMapping(value = "/saveEmployee", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public ResponseEntity < String > saveEmployee(@RequestBody Employee employee) {
        log.info("Saving employee {}",employee.getName());
        employeeRepository.save(employee);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}