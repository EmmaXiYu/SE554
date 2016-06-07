/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.depaul.cdm.se.yuxi.ejb.impl;

import com.depaul.cdm.se.yuxi.ejb.PaymentServiceLocalBean;
import com.depaul.cdm.se.yuxi.persistence.Address;
import com.depaul.cdm.se.yuxi.persistence.Customer;
import com.depaul.cdm.se.yuxi.persistence.PaymentMethod;
import java.util.List;
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
@Stateless  
public class PaymentService implements PaymentServiceLocalBean {
    
     @PersistenceContext(unitName = "jpa-project")
    private EntityManager entityManager;
      @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void addPayment(PaymentMethod pm){
        entityManager.persist(pm);
    
    }
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public List<PaymentMethod> getAllPayment(Long cuId){
        TypedQuery<PaymentMethod> query =
      entityManager.createNamedQuery("findAllPaymentMethodOfAUser", PaymentMethod.class).setParameter("id", cuId);
             
      List<PaymentMethod> results = query.getResultList();
      if(results.size()==0||results==null)
     return null;
      else{
          
         return results;
    }
    }
    
}
