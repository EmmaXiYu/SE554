/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.depaul.cdm.se.yuxi.service;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import com.depaul.cdm.se.yuxi.persistence.*;

/**
 *
 * @author apple
 */
public class CustomerService {
      private EntityManager em;

    public CustomerService() {
        em = Persistence.createEntityManagerFactory("jpaProject").createEntityManager();
    }
    public void SaveCustomer(Customer customer) {
        EntityTransaction tx = em.getTransaction();

        tx.begin();
        Customer customerToUpdate;
        if ((customer.getId()!= null) && (customer.getId() > 0)) {
            customerToUpdate = em.find(Customer.class, customer.getId());
            customerToUpdate.setFirstName(customer.getFirstName());
            customerToUpdate.setLastName(customer.getLastName());
            customerToUpdate.setAge(customer.getAge());
            customerToUpdate.setGender(customer.getGender());
          
        } else {
            customerToUpdate = customer;//? bad idea. need a copy constructor
        }
            
        em.persist(customerToUpdate);
        tx.commit();
    }
}
