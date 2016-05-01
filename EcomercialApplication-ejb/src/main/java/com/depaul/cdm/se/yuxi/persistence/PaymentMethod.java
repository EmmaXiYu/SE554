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
 * @author apple
 */
@Entity
@NamedQuery(name="findAllPayments", query="select b from PaymentMethod b")
public class PaymentMethod  implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Version
    @Column
    private Integer version;
   // private String type;
    private String cardNumber;
    //private String expirationDate;
    //private String code;
    
     public Long getId() {
        return id;

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

    
    
    public String getCardNumber()
    {
    return cardNumber;}
    public void setCardNumber(String cardNumber)
    {this.cardNumber = cardNumber;}
}
