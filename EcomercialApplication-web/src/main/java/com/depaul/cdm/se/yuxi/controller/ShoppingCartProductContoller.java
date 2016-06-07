/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.depaul.cdm.se.yuxi.controller;

import com.depaul.cdm.se.yuxi.ejb.OrderServiceLocalBean;
import com.depaul.cdm.se.yuxi.ejb.ShoppingCartServiceLocalBean;
import com.depaul.cdm.se.yuxi.persistence.Customer;

import com.depaul.cdm.se.yuxi.persistence.Product;
import com.depaul.cdm.se.yuxi.persistence.PurchaseOrder;
import com.depaul.cdm.se.yuxi.persistence.PurchaseOrderItem;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Future;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 *
 * @author apple
 */
@ManagedBean(name = "shoppingCartProductContoller")
@SessionScoped
public class ShoppingCartProductContoller implements Serializable {
     @EJB
    private ShoppingCartServiceLocalBean shoppingCartService;
          @EJB
    private OrderServiceLocalBean orderService;
    private Product product = new Product();
    private int cartCount=0;
    private Map<Product, Integer> cartContents=new HashMap<>();
   private Long orderId;
   private PurchaseOrder afterPaymentSummaryOrder;
   private List<PurchaseOrder> afterPaymentSummaryOrderItems;

    public double getFinalBill() {
         ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
Map<String, Object> sessionMap = externalContext.getSessionMap();
        Long currentUserId = (Long) sessionMap.get("CustomerId");
        return shoppingCartService.getFinalBillOfShoppingCart( currentUserId);
    }

    public void setFinalBill(int finalBill) {
        this.finalBill = finalBill;
    }
    private int finalBill=0;
    
    public ShoppingCartProductContoller() throws NamingException
    {
        
    }
   
    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
     public void add(Product p)
    {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
Map<String, Object> sessionMap = externalContext.getSessionMap();
        Long currentUserId = (Long) sessionMap.get("CustomerId");
    shoppingCartService.addShoppingCartItems(p,currentUserId);
   
    
    }
     
     public void addToCart()
       {
          String id = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("productId");
           Product p=shoppingCartService.returnProduct(id);
           add(p);
          //myCart.add(p);
       }
      public String getProductId()
    {
        return FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("productId");
    }
      public Product getClickedProduct()
       {
           return shoppingCartService.returnProduct(getProductId());
       }

    public List<Product> getProducts() {
         ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
Map<String, Object> sessionMap = externalContext.getSessionMap();
        Long currentUserId = (Long) sessionMap.get("CustomerId");
        return shoppingCartService.getShoppingCartItems(currentUserId);
    }

    public void remove(Product p)
    {
    ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
Map<String, Object> sessionMap = externalContext.getSessionMap();
        Long currentUserId = (Long) sessionMap.get("CustomerId");
    shoppingCartService.removeShoppingCartItems(p,currentUserId);
   
  }
    public int getCartCount()
    {  ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
Map<String, Object> sessionMap = externalContext.getSessionMap();
        Long currentUserId = (Long) sessionMap.get("CustomerId");
    return shoppingCartService.getShoppingCartItemCount(currentUserId);
    }
    
    public Map<Product, Integer> getCartContents()
    {
         ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
Map<String, Object> sessionMap = externalContext.getSessionMap();
        Long currentUserId = (Long) sessionMap.get("CustomerId");
        List<Product> pL=shoppingCartService.getShoppingCartItems(currentUserId);
        for(Product obj: pL)
        {
            if(cartContents.containsKey(obj))
            {
                cartContents.put(obj,cartContents.get(obj)+1);
            }
            else {
            cartContents.put(obj, 1);}
        }
        return cartContents;}
    
    
    public String checkOut() throws NamingException, Exception
    {
         String addressId="1";
         Long paymentId=new Long(1);
                ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
Map<String, Object> sessionMap = externalContext.getSessionMap();
        Long currentUserId = (Long) sessionMap.get("CustomerId");
        
      double finalBill =shoppingCartService.getFinalBillOfShoppingCart(currentUserId);
        
       
      
        double curBalance=shoppingCartService.getCurrentUserBalance(currentUserId);
    
       if(curBalance>=finalBill)
  
       {
       orderId= shoppingCartService.checkout(addressId,paymentId,currentUserId);
         return "OrderInformation.xhtml";}
       else return "NotSufficientBalance.xhtml";
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }
  

    public PurchaseOrder getAfterPaymentSummaryOrder() {
         String addressId="1";
         Long paymentId=new Long(1);
                ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
Map<String, Object> sessionMap = externalContext.getSessionMap();
        Long currentUserId = (Long) sessionMap.get("CustomerId");
       return orderService.viewOneOrder(orderId, currentUserId);
    }

    public void setAfterPaymentSummaryOrder(PurchaseOrder afterPaymentSummaryOrder) {
        this.afterPaymentSummaryOrder = afterPaymentSummaryOrder;
    }

    public void setAfterPaymentSummaryOrderItems(List<PurchaseOrder> afterPaymentSummaryOrderItems) {
        this.afterPaymentSummaryOrderItems = afterPaymentSummaryOrderItems;
    }
           
   public List<PurchaseOrderItem> getAfterPaymentSummaryOrderItems()
   {
        String addressId="1";
         Long paymentId=new Long(1);
                ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
Map<String, Object> sessionMap = externalContext.getSessionMap();
        Long currentUserId = (Long) sessionMap.get("CustomerId");
        return orderService.viewAllOrderItem(orderId, currentUserId);
   }
}
