/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.depaul.cdm.se.yuxi.controller;

import com.depaul.cdm.se.yuxi.ejb.ProductServiceLocalBean;

import com.depaul.cdm.se.yuxi.persistence.Product;
import java.io.Serializable;
import javax.ejb.EJB;

import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

import javax.faces.context.FacesContext;
import javax.naming.NamingException;

/**
 *
 * @author apple
 */
@ManagedBean(name = "product")
@SessionScoped
public class product implements Serializable{
     @EJB
    private ProductServiceLocalBean productService;
        private Product product = new Product();
        

  
        
        @ManagedProperty(value="#{shoppingCartProductContoller}")
        ShoppingCartProductContoller myCart;
        public product()throws NamingException
        {
        }
       public String getProductId()
    {
        return FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("productId");
    }
       
        public ShoppingCartProductContoller getMyCart() {
      
        return myCart;
    }

    public void setMyCart(ShoppingCartProductContoller myCart) {
        this.myCart = myCart;
    }
       public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
       
       public Product getClickedProduct()
       {
           return productService.returnProduct(getProductId());
       }
       public void addToCart()
       {
          String id = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("productId");
           Product p=productService.returnProduct(id);
          //myCart.add(p);
       }
    
}
