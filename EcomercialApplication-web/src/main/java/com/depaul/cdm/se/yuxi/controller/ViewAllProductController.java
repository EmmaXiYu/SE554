/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.depaul.cdm.se.yuxi.controller;

import com.depaul.cdm.se.yuxi.ejb.ProductServiceLocalBean;
import com.depaul.cdm.se.yuxi.ejb.ShoppingCartServiceLocalBean;
import com.depaul.cdm.se.yuxi.persistence.Customer;
import com.depaul.cdm.se.yuxi.persistence.ElectronicProduct;
import com.depaul.cdm.se.yuxi.persistence.FoodProduct;
import com.depaul.cdm.se.yuxi.persistence.Product;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.naming.NamingException;

/**
 *
 * @author apple
 */
@ManagedBean(name = "viewAllProductController")
@SessionScoped
public class ViewAllProductController implements Serializable { 
      @EJB
    private ProductServiceLocalBean productService;
      private Product product = new Product();
    private Customer customer= new Customer();
    
      public ViewAllProductController() throws NamingException
    {
        
    }
     public Customer getCustomer ()
    {
    return customer;
    }
    public void setCustomer(Customer cu)
    {
        this.customer=cu;
    }
    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
    
    public List<Product> getProducts() {
        return productService.viewAllProduct();
    }

    public List<ElectronicProduct> getElectronicProducts() {
        return productService.viewAllElectronicProduct();
    }

    public List<FoodProduct> getFoodProducts() {
        return productService.viewAllFoodProduct();
    }
    
}
