/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.depaul.cdm.se.yuxi.exception;

/**
 *
 * @author apple
 */
public class CustomerIsNotFoundException extends Exception {
   
    private String errorMessage;
    public CustomerIsNotFoundException(){}
   
    public  CustomerIsNotFoundException(String errorMessage)
    {
    super(errorMessage);
    }
        
   
}
