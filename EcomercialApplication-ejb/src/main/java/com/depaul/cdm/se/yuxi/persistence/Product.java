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
 * @author YU XI
 */

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Product implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name="PRODUCT_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long ProductId;
    @Version
    @Column
    private Integer version;
    private String description;
    private String name;
    @OneToMany(cascade=CascadeType.ALL,
            mappedBy="ProductId",fetch= FetchType.EAGER
            )
    private Collection<PurchaseOrder> purchaseOrderCollection;
    
    public Long getId() {
        return ProductId;

    }

    public void setId(Long idB) {
        this.ProductId = idB;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version= version;
    }
    
    public String getDescription(
   ){
    return description;}
    
    public void setDescription(String des)
    {
    this.description = des;}
    
    public String getName()
    {
    return name;}
    public void setName(String name)
    {
    this.name = name;}
    
}
