/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.depaul.cdm.se.yuxi.persistence;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Version;

/**
 *
 * @author apple
 */
@Entity
@NamedQueries({
    @NamedQuery(name="findAllShopingItemOfAUser",query="select b from ShoppingCartItem b WHERE b.customerId.customerId = :id"),
   @NamedQuery(name="findShoppingItemWithId",query="select b from ShoppingCartItem b WHERE b.customerId.customerId = :id and b.productId.ProductId = :pId"),
   
}) 
public class ShoppingCartItem implements Serializable{
    private static final long serialVersionUID = 1L;

    @Version
    @Column
    private Integer version;
    @Id
   // @Column(name = "Order_customer_id")
    @JoinColumn(name = "CUSTOMER_ID",
            referencedColumnName = "CUSTOMER_ID")
    @ManyToOne(optional = false)
    private Customer customerId;

    public Boolean getIfPaid() {
        return ifPaid;
    }

    public void setIfPaid(Boolean ifPaid) {
        this.ifPaid = ifPaid;
    }
    @Id
    //@Column(name = "Order_rpoduct_id")
    @JoinColumn(name = "PRODUCT_ID",
            referencedColumnName = "PRODUCT_ID")
    @ManyToOne(optional = false)
    private Product productId;

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    @Column
    private Boolean ifPaid;
    private int quantity;
    public Customer getCustomer() {
        return customerId;
    }

    public void setCustomer(Customer c_id) {
        this.customerId = c_id;
    }
    
    public Product getProduct()
    {
    return productId;}
    public void setProduct(Product pro){
        this. productId = pro;
    }
 
}
