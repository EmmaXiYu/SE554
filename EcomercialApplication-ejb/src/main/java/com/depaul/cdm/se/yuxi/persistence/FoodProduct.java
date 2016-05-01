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
public class FoodProduct extends Product{

    
   @Temporal(TemporalType.DATE)
   private Date expirationDate;
   private String category;
   
   public Date getExpirationDate()
   {
   return expirationDate;}
   public void setExpirationDate(Date date)
   {
   this.expirationDate = date ;}
   public String getCategory()
   {
       return category;
   }
   public void setCategory(String category)
   {
       this.category = category;
   }
   
     
   
    
}
