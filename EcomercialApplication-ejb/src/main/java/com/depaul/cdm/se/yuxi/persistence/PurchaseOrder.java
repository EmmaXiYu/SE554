/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.depaul.cdm.se.yuxi.persistence;

import java.io.Serializable;
import javax.persistence.*;

/**
 *
 * @author YUXI
 */
@Entity
public class PurchaseOrder implements Serializable {

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
    @Id
    //@Column(name = "Order_rpoduct_id")
    @JoinColumn(name = "PRODUCT_ID",
            referencedColumnName = "PRODUCT_ID")
    @ManyToOne(optional = false)
    private Product productId;

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
