/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.depaul.cdm.se.yuxi.controller;

import com.depaul.cdm.se.yuxi.ejb.OrderServiceLocalBean;
import com.depaul.cdm.se.yuxi.persistence.PurchaseOrder;
import com.depaul.cdm.se.yuxi.persistence.PurchaseOrderItem;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

/**
 *
 * @author apple
 */
@ManagedBean(name = "orderContoller")
@SessionScoped
public class OrderController implements Serializable{
      @EJB
    private OrderServiceLocalBean orderService;
      public List<PurchaseOrder> viewAllOrder()
      {
            ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
Map<String, Object> sessionMap = externalContext.getSessionMap();
        Long currentUserId = (Long) sessionMap.get("CustomerId");
        return orderService.viewAllOrder(currentUserId);
      }
      public List<PurchaseOrderItem> viewAllItemOfAOrder(Long id)
      {
          ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
Map<String, Object> sessionMap = externalContext.getSessionMap();
        Long currentUserId = (Long) sessionMap.get("CustomerId");
        return orderService.viewAllOrderItem(id, currentUserId);
      }
}
