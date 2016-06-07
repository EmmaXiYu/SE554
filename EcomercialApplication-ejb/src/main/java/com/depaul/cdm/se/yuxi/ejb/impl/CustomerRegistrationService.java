/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.depaul.cdm.se.yuxi.ejb.impl;



import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.depaul.cdm.se.yuxi.persistence.*;
import com.depaul.cdm.se.yuxi.ejb.*;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.persistence.TypedQuery;
/**
 *
 * @author apple
 */
@Stateless  

public class CustomerRegistrationService implements CustomerRegistrationServiceLocalBean {

    @PersistenceContext(unitName = "jpa-project")
    private EntityManager entityManager;
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
   
    public Customer register(Customer customer)  {
        if(!checkIfExistedUser(customer.getEmail())){
        customer.setFirstName(customer.getFirstName());
        customer.setLastName(customer.getLastName());
        customer.setGender(customer.getGender());
        customer.setAge(customer.getAge());
        customer.setEmail(customer.getEmail());
        String hashPassword=md5(customer.getPassword());
        customer.setPassword(hashPassword);
        entityManager.persist(customer);
        return customer;}
        else return null;
        
    }
    public boolean checkIfExistedUser(String email)
    {
        TypedQuery<Customer> query =
      entityManager.createNamedQuery("findCustomerwithEmail", Customer.class).setParameter("email", email);
     
      List<Customer> results = query.getResultList();
      if(results.size()==0||results==null)
     return false;
      else{
          Customer customer = results.get(0);
          if(email.equals(customer.getEmail()))
              return true;
          else return false;
      }
  
     
    }
    public static String md5(String input) {
         
        String md5 = null;
         
        if(null == input) return null;
         
        try {
             
        //Create MessageDigest object for MD5
        MessageDigest digest = MessageDigest.getInstance("MD5");
         
        //Update input string in message digest
        digest.update(input.getBytes(), 0, input.length());
 
        //Converts message digest value in base 16 (hex) 
        md5 = new BigInteger(1, digest.digest()).toString(16);
 
        } catch (NoSuchAlgorithmException e) {
 
            e.printStackTrace();
        }
        return md5;
    }
  
    
}
