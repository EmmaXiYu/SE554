/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.depaul.cdm.se.yuxi.ejb.impl;

import com.depaul.cdm.se.yuxi.ejb.CustomerLocalBean;
import com.depaul.cdm.se.yuxi.ejb.CustomerLoginServiceLocalBean;
import com.depaul.cdm.se.yuxi.persistence.Customer;
import java.io.Serializable;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author apple
 */
@Stateless
public class CustomerService implements CustomerLocalBean,Serializable{
       @PersistenceContext(unitName = "jpa-project")
    private EntityManager entityManager;
          @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public boolean depositBalance(Long cusId, double amount){
        System.out.println(""+cusId);
           System.out.println(""+amount);
        Customer c=entityManager.find(Customer.class, cusId);
        System.out.println("user id"+c.getId());
        c.setBalance(c.getBalance()+amount);
        
         return true;
        
    }
       @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public boolean withdrawBalance(Long cusId, double amount){
         System.out.println(""+cusId);
           System.out.println(""+amount);
        Customer c=entityManager.find(Customer.class, cusId);
        System.out.println("user id"+c.getId());
    if(c.getBalance()>=amount){
            c.setBalance(c.getBalance()-amount);
        entityManager.persist(c);
        return true;}
    else return false;
    }
}
