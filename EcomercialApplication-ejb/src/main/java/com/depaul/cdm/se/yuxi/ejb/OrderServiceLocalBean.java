/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.depaul.cdm.se.yuxi.ejb;

import com.depaul.cdm.se.yuxi.persistence.Customer;
import com.depaul.cdm.se.yuxi.persistence.Product;
import com.depaul.cdm.se.yuxi.persistence.PurchaseOrder;
import com.depaul.cdm.se.yuxi.persistence.PurchaseOrderItem;
import java.util.List;

/**
 *
 * @author apple
 */
public interface OrderServiceLocalBean {
    
   /* public boolean addOrder(Customer c, Product p);
    public boolean deleteOrder(Customer c, Product p);*/
    public List<PurchaseOrderItem> viewAllOrderItem(Long orderId,Long customerId);
    public List<PurchaseOrder> viewAllOrder(Long customerId); 
    public PurchaseOrder viewOneOrder(Long orderId,Long customerId);
}
