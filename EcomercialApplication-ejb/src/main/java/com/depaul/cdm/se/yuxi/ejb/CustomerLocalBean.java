/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.depaul.cdm.se.yuxi.ejb;

/**
 *
 * @author apple
 */
public interface CustomerLocalBean {
    
    public boolean depositBalance(Long cusId, double amount);
    public boolean withdrawBalance(Long cusId, double amount);
}
