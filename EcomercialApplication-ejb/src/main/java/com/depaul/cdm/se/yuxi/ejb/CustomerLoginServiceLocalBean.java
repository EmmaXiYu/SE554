/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.depaul.cdm.se.yuxi.ejb;

import com.depaul.cdm.se.yuxi.persistence.Address;
import com.depaul.cdm.se.yuxi.persistence.Customer;

/**
 *
 * @author apple
 */
public interface CustomerLoginServiceLocalBean {
    public Customer login(Customer customer);
    //public String getLoginUserId();
    public void addAddress(Address ad);
 
  
    
}
