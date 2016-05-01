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
@NamedQuery(name = "findAllCustomers", query = "select b from Customer b")
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
    private Integer age;
    
    @OneToMany(cascade = CascadeType.ALL,
            mappedBy="customerId",fetch= FetchType.EAGER
            )
    private Collection<PurchaseOrder> purchaseOrderCollection;
    
    @OneToMany(cascade = CascadeType.ALL,
            mappedBy="customerId",fetch= FetchType.EAGER
            )
        private Collection<Address> addressCollection;
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

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

}
