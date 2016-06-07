/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.depaul.cdm.se.yuxi.ejb;

import com.depaul.cdm.se.yuxi.persistence.ElectronicProduct;
import com.depaul.cdm.se.yuxi.persistence.FoodProduct;
import com.depaul.cdm.se.yuxi.persistence.Product;
import java.util.List;

/**
 *
 * @author apple
 */
public interface ProductServiceLocalBean {
    
    public boolean addElectronicProduct(ElectronicProduct ep);
    public boolean addFoodProduct(FoodProduct fp);
    public boolean deleteProduct(Product p);
   // public boolean checkIfProductExist(Product p);
    public List<Product> viewAllProduct();
    public List<ElectronicProduct> viewAllElectronicProduct();
    public List<FoodProduct> viewAllFoodProduct();
    public Product returnProduct(String productId);
            
    
    
}
