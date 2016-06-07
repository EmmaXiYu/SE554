/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.depaul.cdm.se.yuxi.persistence;

import java.io.Serializable;
import javax.persistence.*;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Version;
import java.util.*;

/**
 *
 * @author apple
 */
@Entity
@NamedQueries({
    @NamedQuery(name="findAllFoodProduct",query="select b from FoodProduct b "),
   
}) 
@DiscriminatorValue(value = "Food_Product")
public class FoodProduct extends Product{

    

   private String expirationDate;
   
   public String getExpirationDate()
   {
   return expirationDate;}
   public void setExpirationDate(String date)
   {
   this.expirationDate = date ;}
   
   
     
   
    
}
