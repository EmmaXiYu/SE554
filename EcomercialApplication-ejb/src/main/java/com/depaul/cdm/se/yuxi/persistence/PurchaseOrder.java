/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.depaul.cdm.se.yuxi.persistence;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Version;

/**
 *
 * @author apple
 */
@Entity
@NamedQueries({
    @NamedQuery(name="findAllOrderOfAUser",query="select b from PurchaseOrder b WHERE b.customerId.customerId = :id"),
   @NamedQuery(name="findOrderWith2Id",query="select b from PurchaseOrder b WHERE b.customerId.customerId = :id and b.orderId = :pId"),
   
}) 
public class PurchaseOrder implements Serializable{
      private static final long serialVersionUID = 1L;

    @Version
    @Column
    private Integer version;
    @Id
    @Column(name="ORDER_ID")
      @GeneratedValue(strategy = GenerationType.AUTO)
    private Long orderId;

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Collection<PurchaseOrderItem> getPurchaseOrderItemCollection() {
        return purchaseOrderItemCollection;
    }

    public void setPurchaseOrderItemCollection(Collection<PurchaseOrderItem> purchaseOrderItemCollection) {
        this.purchaseOrderItemCollection = purchaseOrderItemCollection;
    }
   
    @JoinColumn(name = "CUSTOMER_ID",
            referencedColumnName = "CUSTOMER_ID")
    @ManyToOne(optional = false)
    private Customer customerId;

 @OneToMany(cascade=CascadeType.PERSIST,
            mappedBy="orderId",fetch= FetchType.EAGER
            )
    private Collection<PurchaseOrderItem> purchaseOrderItemCollection;
   
  

    public Address getAddressId() {
        return addressId;
    }

    public void setAddressId(Address addressId) {
        this.addressId = addressId;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

  
  @JoinColumn(name = "ARRESS_ID",
            referencedColumnName = "ARRESS_ID")
    @ManyToOne(optional = false)
    private Address addressId;
    @JoinColumn(name = "PAYMENT_ID",
            referencedColumnName = "PAYMENT_ID")
    @ManyToOne(optional = false)
    private PaymentMethod paymentMethod;
   

    public Customer getCustomer() {
        return customerId;
    }

    public void setCustomer(Customer c_id) {
        this.customerId = c_id;
    }

    public String getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(String orderTime) {
        this.orderTime = orderTime;
    }
       @Column
    private String orderTime;
    
}
