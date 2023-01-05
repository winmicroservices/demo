package com.example.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import com.example.demo.model.Customer;
import com.example.demo.model.CustomerModel;

/**
 * This class extends RepresentationModelAssemblerSupport which is required for Pagination.
 * It converts the Customer Entity to the Customer Model and has the code for it
 */
@Component
public class CustomerModelAssembler extends RepresentationModelAssemblerSupport<Customer, CustomerModel> {

    private final static Logger log = LoggerFactory.getLogger(CustomerModelAssembler.class);

    public CustomerModelAssembler() {
        super(CustomerController.class, CustomerModel.class);
    }

    @Override
    public CustomerModel toModel(Customer entity) {
        CustomerModel model = new CustomerModel();
        // Both CustomerModel and Customer have the same property names. So copy the values from the Entity to the Model
        BeanUtils.copyProperties(entity, model);
        
        try {
            model.add(linkTo(methodOn(CustomerController.class).retrieveCustomer(entity.getId())).withSelfRel());
        } catch (Exception e) {
            log.error(e.getMessage());
        }

        return model;
    }
}
