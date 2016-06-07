/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.depaul.cdm.se.yuxi.controller;

import com.depaul.cdm.se.yuxi.ejb.CustomerLoginServiceLocalBean;
import com.depaul.cdm.se.yuxi.ejb.CustomerRegistrationServiceLocalBean;
import com.depaul.cdm.se.yuxi.persistence.Address;
import com.depaul.cdm.se.yuxi.persistence.Customer;
import java.io.Serializable;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import org.primefaces.component.message.Message;
import org.primefaces.component.messages.Messages;

/**
 *
 * @author apple
 */
@ManagedBean (name="customerController")
@SessionScoped
public class CustomerController implements Serializable {
     @EJB
    private CustomerRegistrationServiceLocalBean registerService;
     @EJB 
     private CustomerLoginServiceLocalBean loginService;
    private Customer customer= new Customer();
    private Customer currentLoginCustomer=null;
     private Address address=new Address();
    public Customer getCustomer ()
    {
    return customer;
    }
    public void setCustomer(Customer cu)
    {
        this.customer=cu;
    }
     public Address getAddress()
    {
        return address;
    }
    public void setAddress(Address ad)
    {
        this.address=ad;
    }
    public void registerNewCustomer()
    {
        customer=registerService.register(customer);
        if(customer!=null){
        
         FacesContext.getCurrentInstance().getApplication().getNavigationHandler().handleNavigation(FacesContext.getCurrentInstance(), null, "login.xhtml");
        }
        else 
        {
            
         FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "error register", "Email already exsists."));
         FacesContext.getCurrentInstance().getApplication().getNavigationHandler().handleNavigation(FacesContext.getCurrentInstance(), null, "register.xhtml");
       
    }
     
    }
    public String login()
    {
        customer=loginService.login(customer);
        if(customer!=null){
            currentLoginCustomer=customer;
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("CustomerId", customer.getId());
         return "SuccessLogin.xhtml";}
        else 
        {
            
         FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "error login", "Email or password is wrong."));
         return "login.xhtml";
    }
      
    }
    
    public Customer getCurrentLoginCustomer()
            {
            return currentLoginCustomer;}
    
    public void addNewAddress()
    {
      
        address.setCustomer(currentLoginCustomer);
        
      loginService.addAddress(address);
    }
    
}
