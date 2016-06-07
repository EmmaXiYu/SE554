/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.depaul.cdm.se.yuxi.persistence;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.*;


/**
 *
 * @author apple
 */
@Entity
@NamedQueries({
    @NamedQuery(name="findAllPaymentMethodOfAUser",query="select b from PaymentMethod b WHERE b.customerId.customerId = :id"),
   
})
public class PaymentMethod  implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
     @Column(name="PAYMENT_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Version
    @Column
    private Integer version;
    @Column
    private String type;
    private String cardNumber;
    @Column
    private String expirationDate;
    @Column
    private String securityCode;
    @JoinColumn(name = "CUSTOMER_ID",
            referencedColumnName = "CUSTOMER_ID")
    @ManyToOne(optional = false)
    private Customer customerId;

     public Long getId() {
        return id;

    }
@OneToMany(cascade=CascadeType.PERSIST,
            mappedBy="paymentMethod",fetch= FetchType.EAGER
            )
    private Collection<PurchaseOrder> purchaseOrderCollection;

    public Collection<PurchaseOrder> getPurchaseOrderCollection() {
        return purchaseOrderCollection;
    }

    public void setPurchaseOrderCollection(Collection<PurchaseOrder> purchaseOrderCollection) {
        this.purchaseOrderCollection = purchaseOrderCollection;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

   public Customer getCustomer()
    {
    return customerId;
    }
    public void setCustomer(Customer c)
    {
    this.customerId= c;
    } 
    
    public String getCardNumber()
    {
    return cardNumber;}
    public void setCardNumber(String cardNumber)
    {this.cardNumber = cardNumber;}
    
    public String getType()
    {
    return type;}
    public void setType(String type)
    {
    this.type=type;}
    public String getExpirationDate (){
        return expirationDate;
    }
    public void setExpirationDate(String expirationDate){
        this.expirationDate=expirationDate;
    }
    public String getSecurityCode()   
    {
        return securityCode;
    }
    public void setSecurityCode(String securityCode)
    {
    this.securityCode=securityCode;}
           
}
