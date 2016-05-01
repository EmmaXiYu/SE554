/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.depaul.cdm.se.yuxi.ejb.impl;

import javax.annotation.security.DenyAll;
import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.interceptor.Interceptors;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.util.List;
import javax.annotation.security.DenyAll;
import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.interceptor.Interceptors;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.depaul.cdm.se.yuxi.persistence.*;
import com.depaul.cdm.se.yuxi.ejb.*;
/**
 *
 * @author apple
 */
@Stateless  

public class CustomerRegistrationService implements CustomerRegistrationServiceLocalBean {

    @PersistenceContext(unitName = "jpa-project")
    private EntityManager entityManager;
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
   
    public void register(String firstName, String lastName, String gender, Integer age )  {
        Customer customer = new Customer();
        customer.setFirstName(firstName);
        customer.setLastName(lastName);
        customer.setGender(gender);
        customer.setAge(age);
        entityManager.persist(customer);
        
    }
  
    
}
