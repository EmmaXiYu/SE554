/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.depaul.cdm.se.yuxi.ejb.impl;
import com.depaul.cdm.se.yuxi.ejb.ShoppingCartServiceLocalBean;

import com.depaul.cdm.se.yuxi.persistence.Address;
import com.depaul.cdm.se.yuxi.persistence.Customer;
import com.depaul.cdm.se.yuxi.persistence.ElectronicProduct;
import com.depaul.cdm.se.yuxi.persistence.FoodProduct;
import com.depaul.cdm.se.yuxi.persistence.PaymentMethod;
import com.depaul.cdm.se.yuxi.persistence.Product;
import com.depaul.cdm.se.yuxi.persistence.PurchaseOrder;
import com.depaul.cdm.se.yuxi.persistence.PurchaseOrderItem;
import com.depaul.cdm.se.yuxi.persistence.ShoppingCartItem;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PreDestroy;
import javax.ejb.AfterBegin;
import javax.ejb.AfterCompletion;
import javax.ejb.PostActivate;
import javax.ejb.PrePassivate;
import javax.ejb.Remove;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.jms.ConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.JMSProducer;
import javax.jms.Queue;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;


/**
 *
 * @author apple
 */
@Stateless
//@StatefulTimeout(value=5, unit= TimeUnit.MINUTES)

public class ShoppingCartService implements ShoppingCartServiceLocalBean, Serializable {
    
    //private List<Product> products =new ArrayList<>();
     @PersistenceContext(unitName = "jpa-project")
    private EntityManager entityManager;
  @AfterBegin
  public void afterBegin()
  {
        System.out.println("ShoppingCart service : AfterBegin");
  }
  @AfterCompletion
  public void afterCompletion()
  {
      System.out.println("ShoppingCart service : AfterCompletion");
      
  }
  @PrePassivate 
  public void prePassivate()
  {
       System.out.println("ShoppingCart service : prePassivate");
  }
   
   @PostActivate
  public void postactivate()
  {
       System.out.println("ShoppingCart service : postActivate");
  }
  @Remove
  public void remove()
  {
      System.out.println("ShoppingCart service : remove");
  }
       @PreDestroy
    public void preDestroy() {
        System.out.println("Inteceptor: Before Destroy");
    }
     @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public double getCurrentUserBalance(Long currentUserId)
    {
        Customer c=entityManager.find(Customer.class, currentUserId);
        double b=c.getBalance();
        return b;
    }
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public Long checkout(String addressId,Long paymentId,Long currentUserId) 
    {  
       
        Customer cc=entityManager.find(Customer.class, currentUserId);
        double b=cc.getBalance();
        double orderBill=getFinalBillOfShoppingCart(currentUserId);
        cc.setBalance(b-orderBill);
        PurchaseOrder po=new PurchaseOrder();
        List<PurchaseOrderItem> list=new ArrayList<>();
         Address a=new Address();
          a.setAddressId(addressId);
         po.setAddressId(a);
         PaymentMethod pm=new PaymentMethod();
         pm.setId(paymentId);
          po.setPaymentMethod(pm);
          Customer c=new Customer();
          c.setId(currentUserId);
          po.setCustomer(c);
          DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	   //get current date time with Date()
	   Date date = new Date();
          po.setOrderTime(dateFormat.format(date));
          entityManager.persist(po);
          po.getOrderId();
         TypedQuery<ShoppingCartItem> query =
      entityManager.createNamedQuery("findAllShopingItemOfAUser", ShoppingCartItem.class).setParameter("id", currentUserId);
             
      List<ShoppingCartItem> results = query.getResultList();
      
      if(results.size()==0&&results==null)
     {
         Long l=new Long(-1);
     return l;}
      else
      {
      for(int i =0;i<results.size();i++)
      {
          
          PurchaseOrderItem poi=new PurchaseOrderItem();
         
          poi.setProduct(results.get(i).getProduct());
          poi.setQuantity(results.get(i).getQuantity());
          poi.setOrderId(po);
          list.add(poi);
          entityManager.persist(poi);
          entityManager.remove(results.get(i));
          entityManager.persist(cc);
          
         
         }
      po.setPurchaseOrderItemCollection(list);
    
        entityManager.persist(po);
       return   po.getOrderId();
      }
    }
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public double getFinalBillOfShoppingCart(Long curUserId)
    {
        int finalBill=0;
         
        TypedQuery<ShoppingCartItem> query =
      entityManager.createNamedQuery("findAllShopingItemOfAUser", ShoppingCartItem.class).setParameter("id", curUserId);
             
      List<ShoppingCartItem> results = query.getResultList();
      
      if(results.size()==0&&results==null)
     {
     return 0;}
      else
      {
      for(int i =0;i<results.size();i++)
      {
          int number=results.get(i).getQuantity();
          for(int j=0;j<number;j++)
          {
               finalBill+=results.get(i).getQuantity()*results.get(i).getProduct().getUnitPrice();
          }
         }}
      return finalBill;
    }
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public List<Product> getShoppingCartItems(Long curUserId)
    {
        
       List<Product> pList=new ArrayList<>();
        TypedQuery<ShoppingCartItem> query =
      entityManager.createNamedQuery("findAllShopingItemOfAUser", ShoppingCartItem.class).setParameter("id", curUserId);
             
      List<ShoppingCartItem> results = query.getResultList();
      
      if(results.size()==0&&results==null)
     {
     return null;}
      else
      {
      for(int i =0;i<results.size();i++)
      {
          int number=results.get(i).getQuantity();
          for(int j=0;j<number;j++)
          {
               pList.add(results.get(i).getProduct());
          }
         }}
      return pList;
    }
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
     public int getShoppingCartItemCount(Long curUserId)
     {
          List<Product> pList=new ArrayList<>();
        TypedQuery<ShoppingCartItem> query =
      entityManager.createNamedQuery("findAllShopingItemOfAUser", ShoppingCartItem.class).setParameter("id", curUserId);
             
      List<ShoppingCartItem> results = query.getResultList();
      
      if(results.size()==0&&results==null)
     {
     return 0;}
      else
      {
          return results.size();
      }
     }
     @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void addShoppingCartItems(Product p,Long curUserId){
       TypedQuery<ShoppingCartItem> query =
      entityManager.createNamedQuery("findShoppingItemWithId", ShoppingCartItem.class).setParameter("id", curUserId).setParameter("pId", p.getProductId());
             
      List<ShoppingCartItem> results = query.getResultList();
      if(!results.isEmpty()&&results!=null)
     {
         int quantity=results.get(0).getQuantity();
        
         results.get(0).setQuantity(quantity+1);
          entityManager.persist(results.get(0));
          
         
    }
      else {
        ShoppingCartItem po=new ShoppingCartItem();
        Customer c=new Customer();
        c.setId(curUserId);
        po.setCustomer(c);
        po.setProduct(p);
        po.setIfPaid(false);
        po.setQuantity(1);
        po.setIfPaid(false);
        entityManager.persist(po);}
          
    }
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void removeShoppingCartItems(Product p,Long curUserId)
    {
       TypedQuery<ShoppingCartItem> query =
      entityManager.createNamedQuery("findShoppingItemWithId", ShoppingCartItem.class).setParameter("id", curUserId).setParameter("pId", p.getProductId());
             
      List<ShoppingCartItem> results = query.getResultList();
      if(!results.isEmpty()&&results!=null)
     {
         int quantity=results.get(0).getQuantity();
          if(quantity>1)
          { results.get(0).setQuantity(quantity-1);
          entityManager.persist(results.get(0));
          }
          else if(quantity==1)
         entityManager.remove(results.get(0));
    }
    }
     @Override
        @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
     public List<Product> viewAllProduct(){
         TypedQuery<Product> query =
      entityManager.createNamedQuery("findAllProduct", Product.class);
      List<Product> results = query.getResultList();
      if(results.isEmpty()||results==null)
     return null;
      else{
          
         return results;
     }
     }
    
     @Override
        @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
     public List<ElectronicProduct> viewAllElectronicProduct(){
          TypedQuery<ElectronicProduct> query =
      entityManager.createNamedQuery("findAllElectronicProduct", ElectronicProduct.class);
      List<ElectronicProduct> results = query.getResultList();
      if( results.isEmpty()||results==null)
     return null;
      else{
          
         return results;
       
    }}
     @Override
        @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public List<FoodProduct>  viewAllFoodProduct(){      
        TypedQuery<FoodProduct> query =
      entityManager.createNamedQuery("findAllFoodProduct", FoodProduct.class);
      List<FoodProduct> results = query.getResultList();
      if(results.isEmpty()||results==null)
     return null;
      else{
          
         return results;
       
    }}
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public Product returnProduct(String productId){
        TypedQuery<Product> query =
      entityManager.createNamedQuery("findAProductWithId", Product.class).setParameter("ProductId", Integer.parseInt(productId));
      List<Product> results = query.getResultList();
      if(results.isEmpty()||results==null)
     return null;
      else{
          
         return results.get(0);
       
    }
      
}}
