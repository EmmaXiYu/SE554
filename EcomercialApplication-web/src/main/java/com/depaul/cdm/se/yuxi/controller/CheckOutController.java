/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.depaul.cdm.se.yuxi.controller;

import com.depaul.cdm.se.yuxi.ejb.AddressServiceLocalBean;
import com.depaul.cdm.se.yuxi.ejb.PaymentServiceLocalBean;
import com.depaul.cdm.se.yuxi.persistence.Address;
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
import javax.naming.NamingException;

/**
 *
 * @author apple
 */
@ManagedBean(name = "checkOutController")
@SessionScoped
public class CheckOutController implements Serializable{

    public List<Address> getAddressList() {
          ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
Map<String, Object> sessionMap = externalContext.getSessionMap();
        Long currentUserId = (Long) sessionMap.get("CustomerId");
    return addressService.getAllAddress(currentUserId);
    }

    public void setAddressList(List<Address> addressList) {
        this.addressList = addressList;
    }

    public List<PaymentMethod> getPaymentMethodList() {
         ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
Map<String, Object> sessionMap = externalContext.getSessionMap();
        Long currentUserId = (Long) sessionMap.get("CustomerId");
        return paymentService.getAllPayment(currentUserId);
    }

    public void setPaymentMethodList(List<PaymentMethod> paymentMethodList) {
        this.paymentMethodList = paymentMethodList;
    }
    
    @EJB
    private AddressServiceLocalBean addressService;
    @EJB
    private PaymentServiceLocalBean paymentService;

    public String getAddressId() {
        return addressId;
    }

    public void setAddressId(String addressId) {
        this.addressId = addressId;
    }

    public Long getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(Long paymentId) {
        this.paymentId = paymentId;
    }
    private List<Address> addressList=new ArrayList<>();
    private List<PaymentMethod> paymentMethodList=new ArrayList<>();
    private String addressId;
    private Long paymentId;
    public CheckOutController() throws NamingException
    {
        
    }
    
}
