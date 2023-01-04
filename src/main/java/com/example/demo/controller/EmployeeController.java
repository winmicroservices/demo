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

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;

import com.example.demo.model.Employee;
import com.example.demo.rest.repository.EmployeeRepository;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/v1/api", produces = "application/hal+json")
public class EmployeeController {

    private final static Logger log = LoggerFactory.getLogger(EmployeeController.class);

    @Autowired
    EmployeeRepository employeeRepository;

    // @GetMapping("/employee")
    // public List<Employee> retrieveAllEmployees() {
    //     return employeeRepository.findAll();
    // }

    @GetMapping("/employee")
    CollectionModel<EntityModel<Employee>> retrieveAllEmployees() {
        List<EntityModel<Employee>> items = employeeRepository.findAll().stream().map(item -> EntityModel.of(item,
                linkTo(methodOn(EmployeeController.class).retrieveAllEmployees()).withRel("items")))
                .collect(Collectors.toList());
        return CollectionModel.of(items, linkTo(methodOn(EmployeeController.class).retrieveAllEmployees()).withSelfRel());
    }


    @GetMapping("/employee/{id}")
    public EntityModel<Employee> retrieveEmployee(@PathVariable long id) throws Exception {
        Employee employee = employeeRepository.getReferenceById(id);

        if (employee == null)
            throw new Exception("No id-" + id);
            
        return EntityModel.of(employee, 
          linkTo(methodOn(EmployeeController.class).retrieveEmployee(id)).withSelfRel(),
          linkTo(methodOn(EmployeeController.class).retrieveAllEmployees()).withRel("employees"));
    }

    @RequestMapping(value = "/employee/create", method = RequestMethod.POST, consumes = "application/json")
    public EntityModel<Employee> saveEmployee(@RequestBody Employee employee) throws Exception {
        // TODO: Return what you would return on a get.
        log.info("Saving employee {}",employee.getName());
        Employee e = employeeRepository.save(employee);
        return retrieveEmployee(e.getId());
        // return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}