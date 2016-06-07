/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.depaul.cdm.se.yuxi.ejb.impl;

import com.depaul.cdm.se.yuxi.ejb.OrderServiceLocalBean;
import com.depaul.cdm.se.yuxi.persistence.Address;
import com.depaul.cdm.se.yuxi.persistence.Customer;
import com.depaul.cdm.se.yuxi.persistence.Product;
import com.depaul.cdm.se.yuxi.persistence.PurchaseOrder;
import com.depaul.cdm.se.yuxi.persistence.PurchaseOrderItem;
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
public class OrderService implements OrderServiceLocalBean{
    @PersistenceContext(unitName = "jpa-project")
    private EntityManager entityManager;
    
    /*
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
   public boolean addOrder(Customer c, Product p){
       try{
       PurchaseOrderItem po=new PurchaseOrderItem();
       po.setCustomer(c);
       po.setProduct(p);
       entityManager.persist(po);
       return true;}
       catch(Exception e)
       {
           return false;
       }
   }
   @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public boolean deleteOrder(Customer c, Product p){
        try{
       PurchaseOrderItem po=new PurchaseOrderItem();
       po.setCustomer(c);
       po.setProduct(p);
       entityManager.remove(po);
       return true;}
       catch(Exception e)
       {
           return false;
       }
    }
     @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public List<PurchaseOrderItem> viewAllOrder(Customer c){
          TypedQuery<PurchaseOrderItem> query =
      entityManager.createNamedQuery("findAllOrderOfAUser", PurchaseOrderItem.class).setParameter("id", c.getId());
             
      List<PurchaseOrderItem> results = query.getResultList();
      if(results.size()==0||results==null)
     return null;
      else{
          
         return results;
    }
    } */
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
      public List<PurchaseOrderItem> viewAllOrderItem(Long orderId,Long customerId)
      {
          TypedQuery<PurchaseOrderItem> query =
      entityManager.createNamedQuery("findOrderItemsWithId", PurchaseOrderItem.class).setParameter("pId", orderId);
             
      List<PurchaseOrderItem> results = query.getResultList();
      if(results.size()==0||results==null)
     return null;
      else{
          
         return results;
    }
      }
      @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public List<PurchaseOrder> viewAllOrder(Long customerId)
    {
        TypedQuery<PurchaseOrder> query =
      entityManager.createNamedQuery("findAllOrderOfAUser", PurchaseOrder.class).setParameter("id", customerId);
             
      List<PurchaseOrder> results = query.getResultList();
      if(results.size()==0||results==null)
     return null;
      else{
          
         return results;
    }
    }
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
       public PurchaseOrder viewOneOrder(Long orderId,Long customerId)
       {
             TypedQuery<PurchaseOrder> query =
      entityManager.createNamedQuery("findOrderWith2Id", PurchaseOrder.class).setParameter("id", customerId).setParameter("pId", orderId);
             
      List<PurchaseOrder> results = query.getResultList();
      if(results.size()==0||results==null)
     return null;
      else{
          
         return results.get(0);
    }
       }
}
