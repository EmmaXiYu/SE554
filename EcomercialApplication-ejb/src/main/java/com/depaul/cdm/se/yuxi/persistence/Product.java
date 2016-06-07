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
@NamedQueries({
    @NamedQuery(name="findAllProduct",query="select b from Product b "),
    @NamedQuery(name="findAProductWithId",query="select b from Product b WHERE b.ProductId = :ProductId"),
}) 
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "PRODUCT_TYPE")
public class Product implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name="PRODUCT_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long ProductId;
    @Version
    @Column
    private Integer version;
    @Column
    private String description;
    @Column
    private String name;
    @Column 
    private int number;

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }
    @Column
    private double unitPrice ;
    public Long getProductId() {
        return ProductId;
    }

    public Collection<PurchaseOrderItem> getPurchaseOrderCollection() {
        return purchaseOrderCollection;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

    public Collection<ShoppingCartItem> getShoppingCartItemCollection() {
        return shoppingCartItemCollection;
    }

    public void setShoppingCartItemCollection(Collection<ShoppingCartItem> shoppingCartItemCollection) {
        this.shoppingCartItemCollection = shoppingCartItemCollection;
    }
    @OneToMany(cascade=CascadeType.PERSIST,
            mappedBy="ProductId",fetch= FetchType.EAGER
            )
    private Collection<PurchaseOrderItem> purchaseOrderCollection;
    
     @OneToMany(cascade=CascadeType.PERSIST,
            mappedBy="ProductId",fetch= FetchType.EAGER
            )
    private Collection<ShoppingCartItem> shoppingCartItemCollection;
    
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
    
   @Transient
   private String expirationDate;
   private String category;
   
   public String getExpirationDate()
   {
   return expirationDate;}
   public void setExpirationDate(String date)
   {
   this.expirationDate = date ;}
   public String getCategory()
   {
       return category;
   }
   public void setCategory(String category)
   {
       this.category = category;
   }
     @Transient
   private String manufacture;
    
    public String getManufacture() {
        return manufacture;
    }

    public void setManufacture(String m) {
        this.manufacture = m;
    }
}
