/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.depaul.cdm.se.yuxi.controller;

import com.depaul.cdm.se.yuxi.ejb.AddressServiceLocalBean;

import com.depaul.cdm.se.yuxi.persistence.Address;
import com.depaul.cdm.se.yuxi.persistence.Customer;
import com.depaul.cdm.se.yuxi.persistence.Product;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.SessionContext;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;


/**
 *
 * @author Yu Xi
 */
@ManagedBean (name="customerAddressController")
@SessionScoped 
public class CustomerAddressController implements Serializable{
    @EJB 
    private AddressServiceLocalBean addressService;
  
    private Address address=new Address();
    @ManagedProperty(value="#{customerController}")
    private CustomerController customerController;
 
    
    public CustomerController getCustomerController(){
        return customerController;
    }
    public void setCustomerController(CustomerController customerContoller){
        this.customerController = customerContoller;
    }
    public List<Address> getAddressList() {
    ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
Map<String, Object> sessionMap = externalContext.getSessionMap();
        Long currentUserId = (Long) sessionMap.get("CustomerId");
        return addressService.getAllAddress(currentUserId);
    }

    public Address getAddress()
    {
        return address;
    }
    public void setAddress(Address ad)
    {
        this.address=ad;
    }
    public String addNewAddress()
    {
            ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
Map<String, Object> sessionMap = externalContext.getSessionMap();
        Long currentUserId = (Long) sessionMap.get("CustomerId");
       Customer c= new Customer();
       c.setId(currentUserId);
        address.setCustomer(c);
        
      addressService.addAddress(address);
      
      return "addAddress.xhtml";
    }
    public String getAllAddress()
    {
          Customer c= new Customer();
       c.setId((long)1);
     
      return "addAddress.xhtml";
    }
    
}
