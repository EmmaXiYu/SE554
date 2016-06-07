/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.depaul.cdm.se.yuxi.ejb.impl;

import com.depaul.cdm.se.yuxi.ejb.CustomerLoginServiceLocalBean;
import com.depaul.cdm.se.yuxi.persistence.Address;
import com.depaul.cdm.se.yuxi.persistence.Customer;
import java.io.Serializable;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.Principal;
import java.util.List;
import java.util.concurrent.TimeUnit;
import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Stateful;
import javax.ejb.StatefulTimeout;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author apple
 */

@Stateful
@StatefulTimeout(value=1, unit= TimeUnit.MINUTES)
public class CustomerLoginService implements CustomerLoginServiceLocalBean,Serializable {
    
    
    @PersistenceContext(unitName = "jpa-project")
    private EntityManager entityManager;
   // @Resource
   // private SessionContext context;
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public Customer login(Customer customer){
     TypedQuery<Customer> query =
      entityManager.createNamedQuery("AutenticateUserWithEmailAndPassword", Customer.class).setParameter("email", customer.getEmail()).
              setParameter("password", md5(customer.getPassword()));
      List<Customer> results = query.getResultList();
      if(results.size()==0||results==null)
     return null;
      else{
          Customer cu = results.get(0);
         return cu;
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
//     public String getLoginUserId(){
//          Principal principal = context.getCallerPrincipal();
//     return principal.getName();
//     }
    
    
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void addAddress(Address ad){
        entityManager.persist(ad);
    
    }
}
