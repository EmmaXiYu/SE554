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
 * @author yu xi
 */
@Entity

public class Address implements Serializable {

    private static final long serialVersionUID = 1L;
     @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;
    
    @Column(name="Address_Line1")
    private String addressLine1;
    @Column(name="Address_Line2")
    private String addressLine2;
    @Column(name="Address_City")
    private String city;
    @Column(name="Address_State")
    private String state;
    @Column(name="Address_Zip")
    private String zipCode;

    @JoinColumn(name = "CUSTOMER_ID",
            referencedColumnName = "CUSTOMER_ID")
    @ManyToOne(optional = false)
    private Customer customerId;
    public String getId()
    {return id;}
    public void setId(String id)
    {this.id = id;}
    public Customer getCustomer()
    {
    return customerId;
    }
    public void setCustomer(Customer c)
    {
    this.customerId= c;
    }
    public String getAddressLine1() {
        return addressLine1;
    }

    public void setAddressLine1(String address1) {
        addressLine1 = address1;
    }

    public String getAddressLine2() {
        return addressLine2;
    }

    public void setAddressLine2(String address2) {
        addressLine2 = address2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city= city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zip) {
        zipCode = zip;
    }
}
