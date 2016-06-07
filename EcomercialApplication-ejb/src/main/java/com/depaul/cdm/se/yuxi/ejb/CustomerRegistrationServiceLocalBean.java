/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.depaul.cdm.se.yuxi.ejb;

import com.depaul.cdm.se.yuxi.persistence.Customer;

/**
 *
 * @author apple
 */
public interface CustomerRegistrationServiceLocalBean {
    
    public Customer register(Customer customer);
    public boolean checkIfExistedUser(String email);
   // public void changePassword();
    //public void retrievePassword();
    //public void changeUserInformation();
    
    
    
    
}
