/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.depaul.cdm.se.yuxi.controller;

import com.depaul.cdm.se.yuxi.ejb.AddressServiceLocalBean;
import com.depaul.cdm.se.yuxi.ejb.PaymentServiceLocalBean;
import com.depaul.cdm.se.yuxi.persistence.Address;
import com.depaul.cdm.se.yuxi.persistence.Customer;
import com.depaul.cdm.se.yuxi.persistence.PaymentMethod;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

/**
 *
 * @author apple
 */
@ManagedBean (name="customerPaymentController")
@SessionScoped 
public class CustomerPaymentController implements Serializable {
     @EJB 
    private PaymentServiceLocalBean paymentService;
    
    private PaymentMethod paymentMethod =new PaymentMethod();
    public PaymentMethod getPaymentMethod()
    {
        return paymentMethod;
    }
    public void setPaymentMethod(PaymentMethod paymentMethod)
    {
        this.paymentMethod=paymentMethod;
    }
     public List<PaymentMethod> getPaymentMethodList() {
           Customer c= new Customer();
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
Map<String, Object> sessionMap = externalContext.getSessionMap();
        Long currentUserId = (Long) sessionMap.get("CustomerId");
        return paymentService.getAllPayment(currentUserId);
    }
    public String addPayment()
    {
       Customer c= new Customer();
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
Map<String, Object> sessionMap = externalContext.getSessionMap();
        Long currentUserId = (Long) sessionMap.get("CustomerId");
       c.setId(currentUserId);
        paymentMethod.setCustomer(c);
        
      paymentService.addPayment(paymentMethod);
   
      return "addPayment.xhtml";
    }
    public String getAllAddress()
    {
          Customer c= new Customer();
       c.setId((long)1);
        
    
      return "addPayment.xhtml";
    }
}
