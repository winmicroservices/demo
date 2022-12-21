package com.example.demo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
  
// class can be mapped to a table
@Table(name = "employee")
@Entity 
public class Employee {
    
    // @ID This annotation specifies 
    // the primary key of the entity.
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) 
    private long id;
    private String name;
    private String city;
  
    public Employee() {
        super();
    }
    public Employee(String name, String city) {
        super();
        this.name = name;
        this.city = city;
    }
  
    public String getName() {
        return name;
    }
  
    public void setName(String name) {
        this.name = name;
    }
  
    public String getCity() {
        return city;
    }
  
    public void setCity(String city) {
        this.city = city;
    }
  
}