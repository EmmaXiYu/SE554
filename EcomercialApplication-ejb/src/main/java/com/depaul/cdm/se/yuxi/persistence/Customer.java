/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.depaul.cdm.se.yuxi.persistence;

import java.io.Serializable;
import javax.persistence.*;
import java.util.*;

/**
 *
 * @author YUXI
 */
@Entity
@NamedQueries({
    @NamedQuery(name="findAllCustomers",query="select b from Customer b"),
    @NamedQuery(name="findCustomerwithEmail",
                query="SELECT c FROM Customer c WHERE c.email = :email"),
    @NamedQuery(name="AutenticateUserWithEmailAndPassword", query="SELECT c FROM Customer c WHERE c.email = :email AND c.password = :password  "),
}) 


public class Customer
        implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name="CUSTOMER_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long customerId;
    @Version
    @Column
    private Integer version;
    @Column
    private String firstName;
    @Column
    private String lastName;
    @Column
    private String gender;
    @Column
    private String age;
    @Column 
    private String email;
    @Column 
    private String password;
    @Column
    private double balance;

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
    
    
    @OneToMany(cascade = CascadeType.PERSIST,
            mappedBy="customerId",fetch= FetchType.EAGER
            )
    private Collection<ShoppingCartItem> shoppingCartItemCollection;

    public Collection<ShoppingCartItem> getShoppingCartItemCollection() {
        return shoppingCartItemCollection;
    }

    public void setShoppingCartItemCollection(Collection<ShoppingCartItem> shoppingCartItemCollection) {
        this.shoppingCartItemCollection = shoppingCartItemCollection;
    }
    @OneToMany(cascade = CascadeType.PERSIST,
            mappedBy="customerId",fetch= FetchType.EAGER
            )
    private Collection<PurchaseOrder> purchaseOrderCollection;
    @OneToMany(cascade = CascadeType.PERSIST,
            mappedBy="customerId",fetch= FetchType.EAGER
            )
        private Collection<Address> addressCollection;
    @OneToMany(cascade = CascadeType.PERSIST,
            mappedBy="customerId",fetch= FetchType.EAGER
            )
        private Collection<PaymentMethod> paymentMethodCollection;
    public Long getId() {
        return customerId;

    }

    public void setId(Long id) {
        this.customerId = id;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public String getFirstName() {

        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender( 
        String genderText) {
        this.gender = genderText;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }
    public String getEmail()
    {
    return email;}
    public void setEmail(String email)
    {
    this.email=email;
    }
    public void setPassword(String password)
    {
    this.password=password;
    }
    public String getPassword()
    {
    return password;
    }

    public Collection<PurchaseOrder> getPurchaseOrderCollection() {
        return purchaseOrderCollection;
    }

    public void setPurchaseOrderCollection(Collection<PurchaseOrder> purchaseOrderCollection) {
        this.purchaseOrderCollection = purchaseOrderCollection;
    }
    
  
    public Collection<PaymentMethod>  getPaymentMethodCollection()
    {return  paymentMethodCollection;
    }
    public void setPaymentMethodCollection(Collection<PaymentMethod> paymentMethodCollection)
    {
        this.paymentMethodCollection=paymentMethodCollection;
    }

    public Collection<Address>  getAddressCollection()
    {return  addressCollection;
    }
    public void setAddressCollection(Collection<Address> addressCollection)
    {
        this.addressCollection=addressCollection;
    }
}
