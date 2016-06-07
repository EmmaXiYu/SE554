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
@NamedQueries({
    @NamedQuery(name="findAllElectronicProduct",query="select b from ElectronicProduct b "),
   
}) 
@DiscriminatorValue(value = "Electronic_Product")
public class ElectronicProduct extends Product {

    private String manufacture;
    
    public String getManufacture() {
        return manufacture;
    }

    public void setManufacture(String m) {
        this.manufacture = m;
    }
    
   
}
