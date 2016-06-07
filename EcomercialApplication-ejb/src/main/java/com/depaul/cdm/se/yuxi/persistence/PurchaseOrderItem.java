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
 * SELECT e FROM Employee e JOIN e.projects p JOIN e.projects p2 WHERE p.name = :p1 and p2.name = :p2
 */
@Entity
@NamedQueries({
    
   @NamedQuery(name="findOrderItemsWithId",query="select b from PurchaseOrderItem b WHERE b.orderId.orderId = :pId"),
   
}) 

public class PurchaseOrderItem implements Serializable {

    private static final long serialVersionUID = 1L;

    @Version
    @Column
    private Integer version;
  
      @Id
    //@Column(name = "Order_rpoduct_id")
    @JoinColumn(name = "ORDER_ID",
            referencedColumnName = "ORDER_ID")
    @ManyToOne(optional = false)
    private PurchaseOrder orderId;
    @Id
    //@Column(name = "Order_rpoduct_id")
    @JoinColumn(name = "PRODUCT_ID",
            referencedColumnName = "PRODUCT_ID")
    @ManyToOne(optional = false)
    private Product productId;

    public PurchaseOrder getOrderId() {
        return orderId;
    }

    public void setOrderId(PurchaseOrder orderId) {
        this.orderId = orderId;
    }
    
    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
   
    private int quantity;
  
    
    public Product getProduct()
    {
    return productId;}
    public void setProduct(Product pro){
        this. productId = pro;
    }

}
