/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.depaul.cdm.se.yuxi.persistence;

import javax.persistence.*;

/**
 *
 * @author apple
 */
@Entity
public class ElectronicProduct extends Product {

    private String manufacture;
     private String category;

    public String getManufacture() {
        return manufacture;
    }

    public void setManufacture(String m) {
        this.manufacture = m;
    }
    
     public String getCategory()
   {
       return category;
   }
   public void setCategory(String category)
   {
       this.category = category;
   }
}
