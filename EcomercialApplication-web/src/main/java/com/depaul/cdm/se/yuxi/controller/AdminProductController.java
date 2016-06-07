/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.depaul.cdm.se.yuxi.controller;

import com.depaul.cdm.se.yuxi.ejb.ProductServiceLocalBean;
import com.depaul.cdm.se.yuxi.persistence.ElectronicProduct;
import com.depaul.cdm.se.yuxi.persistence.FoodProduct;
import com.depaul.cdm.se.yuxi.persistence.Product;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

/**
 *
 * @author apple
 */
@ManagedBean(name = "adminProductController")
@SessionScoped
public class AdminProductController implements Serializable {

    @EJB
    private ProductServiceLocalBean productService;
    private Product product = new Product();

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public void addProduct() {
        if (product.getCategory().equals("Electronic Product")) {
            ElectronicProduct electronicProduct = new ElectronicProduct();
            electronicProduct.setName(product.getName());
            electronicProduct.setDescription(product.getDescription());
            electronicProduct.setCategory(product.getCategory());
            electronicProduct.setManufacture(product.getManufacture());
            electronicProduct.setNumber(product.getNumber());
            electronicProduct.setUnitPrice(product.getUnitPrice());
            productService.addElectronicProduct(electronicProduct);
        } else if (product.getCategory().equals("Food Product")) {
            FoodProduct foodProduct = new FoodProduct();
            foodProduct.setName(product.getName());
            foodProduct.setDescription(product.getDescription());
            foodProduct.setCategory(product.getCategory());
            foodProduct.setExpirationDate(product.getExpirationDate());
            foodProduct.setNumber(product.getNumber());
            foodProduct.setUnitPrice(product.getUnitPrice());
            productService.addFoodProduct(foodProduct);
        }

    }

    public List<Product> getProducts() {
        return productService.viewAllProduct();
    }

    public List<ElectronicProduct> getElectronicProducts() {
        return productService.viewAllElectronicProduct();
    }

    public List<FoodProduct> getFoodProducts() {
        return productService.viewAllFoodProduct();
    }
    
 

}
