/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.depaul.cdm.se.yuxi.ejb.impl;

import com.depaul.cdm.se.yuxi.persistence.ElectronicProduct;
import com.depaul.cdm.se.yuxi.persistence.FoodProduct;
import com.depaul.cdm.se.yuxi.persistence.Product;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.depaul.cdm.se.yuxi.ejb.ProductServiceLocalBean;
import static com.depaul.cdm.se.yuxi.ejb.impl.CustomerLoginService.md5;
import com.depaul.cdm.se.yuxi.persistence.Customer;
import java.util.List;
import javax.persistence.TypedQuery;

/**
 *
 * @author apple
 */
@Stateless
public class ProductService implements ProductServiceLocalBean{
     @PersistenceContext(unitName = "jpa-project")
    private EntityManager entityManager;
   // @Resource
   // private SessionContext context;
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
     public boolean addElectronicProduct(ElectronicProduct ep){
          try{
        entityManager.persist(ep);
        return true;}
        catch(Exception e){
            return false;
        }
     }
        @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public boolean addFoodProduct(FoodProduct fp){
        try{
        entityManager.persist(fp);
        return true;}
        catch(Exception e){
            return false;
        }
        
    }
       @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public boolean deleteProduct(Product p){
        try{
        entityManager.remove(p);
        return true;}
        catch(Exception e){
            return false;
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
    }
}
