package com.example.demo.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Employee;
import com.example.demo.rest.repository.EmployeeRepository;

@RestController
@RequestMapping(value = "/v1/api", produces = "application/hal+json")
public class EmployeeController {

    private final static Logger log = LoggerFactory.getLogger(EmployeeController.class);

    @Autowired
    EmployeeRepository employeeRepository;

    @GetMapping("/employees")
    CollectionModel<EntityModel<Employee>> retrieveAllEmployees() {
        List<EntityModel<Employee>> items = employeeRepository.findAll().stream().map(item -> EntityModel.of(item,
                linkTo(methodOn(EmployeeController.class).retrieveAllEmployees()).withRel("employees")))
                .collect(Collectors.toList());
        for(EntityModel<Employee> em : items) {
            Employee e = em.getContent();
            try {
                em.add(linkTo(methodOn(EmployeeController.class).retrieveEmployee(e.getId())).withSelfRel());
            } catch (Exception e1) {
                log.error(e1.getMessage());
            }
        }
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
        log.info("Saving employee {}",employee.getName());
        Employee e = employeeRepository.save(employee);
        return retrieveEmployee(e.getId());
    }
}