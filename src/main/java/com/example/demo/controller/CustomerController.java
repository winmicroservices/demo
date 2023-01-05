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

import com.example.demo.model.Customer;
import com.example.demo.rest.repository.CustomerRepository;

@RestController
@RequestMapping(value = "/v1/api", produces = "application/hal+json")
public class CustomerController {

    private final static Logger log = LoggerFactory.getLogger(CustomerController.class);

    @Autowired
    CustomerRepository customerRepository;

    @GetMapping("/customers")
    public CollectionModel<EntityModel<Customer>> retrieveAllCustomers() {
        List<EntityModel<Customer>> items = customerRepository.findAll().stream().map(item -> EntityModel.of(item,
                linkTo(methodOn(CustomerController.class).retrieveAllCustomers()).withRel("employees")))
                .collect(Collectors.toList());
        for(EntityModel<Customer> em : items) {
            Customer customer = em.getContent();
            try {
                em.add(linkTo(methodOn(CustomerController.class).retrieveCustomer(customer.getId())).withSelfRel());
            } catch (Exception e) {
                log.error(e.getMessage());
            }
        }
        return CollectionModel.of(items, linkTo(methodOn(CustomerController.class).retrieveAllCustomers()).withSelfRel());
    }


    @GetMapping("/customer/{id}")
    public EntityModel<Customer> retrieveCustomer(@PathVariable long id) throws Exception {
        Customer employee = customerRepository.getReferenceById(id);

        if (employee == null) {
            throw new Exception("No id-" + id);
        }
            
        return EntityModel.of(employee, 
          linkTo(methodOn(CustomerController.class).retrieveCustomer(id)).withSelfRel(),
          linkTo(methodOn(CustomerController.class).retrieveAllCustomers()).withRel("customers"));
    }

    @RequestMapping(value = "/customer/create", method = RequestMethod.POST, consumes = "application/json")
    public EntityModel<Customer> saveCustomer(@RequestBody Customer customer) throws Exception {
        log.info("Saving customer {}",customer.getName());
        Customer savedEmployee = customerRepository.save(customer);
        return retrieveCustomer(savedEmployee.getId());
    }
}