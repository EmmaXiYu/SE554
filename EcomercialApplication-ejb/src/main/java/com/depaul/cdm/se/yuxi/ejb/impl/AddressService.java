/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.depaul.cdm.se.yuxi.ejb.impl;

import com.depaul.cdm.se.yuxi.ejb.AddressServiceLocalBean;
import static com.depaul.cdm.se.yuxi.ejb.impl.CustomerLoginService.md5;
import com.depaul.cdm.se.yuxi.persistence.Address;
import com.depaul.cdm.se.yuxi.persistence.Customer;
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
public class AddressService implements AddressServiceLocalBean{
     @PersistenceContext(unitName = "jpa-project")
    private EntityManager entityManager;
      @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void addAddress(Address ad){
        entityManager.persist(ad);
    
    }
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public List<Address> getAllAddress(Long cuId){
        TypedQuery<Address> query =
      entityManager.createNamedQuery("findAllAddressOfAUser", Address.class).setParameter("id",cuId);
             
      List<Address> results = query.getResultList();
      if(results.size()==0||results==null)
     return null;
      else{
          
         return results;
    }
    }
}
