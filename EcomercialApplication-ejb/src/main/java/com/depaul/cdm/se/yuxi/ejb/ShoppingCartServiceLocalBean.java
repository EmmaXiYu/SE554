/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.depaul.cdm.se.yuxi.ejb;

import com.depaul.cdm.se.yuxi.persistence.Customer;
import com.depaul.cdm.se.yuxi.persistence.ElectronicProduct;
import com.depaul.cdm.se.yuxi.persistence.FoodProduct;
import com.depaul.cdm.se.yuxi.persistence.Product;
import java.util.List;

/**
 *
 * @author apple
 */
public interface ShoppingCartServiceLocalBean {
        //public Customer login(Customer customer);
   public List<Product> viewAllProduct();
   public List<ElectronicProduct> viewAllElectronicProduct();
   public List<FoodProduct> viewAllFoodProduct();
    public Product returnProduct(String productId);
    public List<Product> getShoppingCartItems(Long curUserId);
    public void addShoppingCartItems(Product p,Long curUserId);
    public void removeShoppingCartItems(Product p, Long curUserId);
    public int getShoppingCartItemCount(Long curUserId);
    public double getFinalBillOfShoppingCart(Long curUserId);
    public Long checkout(String addressId,Long paymentId,Long currentUserId);
    public double getCurrentUserBalance(Long currentUserId);
}
