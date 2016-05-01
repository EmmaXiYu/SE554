/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.depaul.cdm.se.yuxi.service;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import com.depaul.cdm.se.yuxi.persistence.*;
import javax.persistence.PersistenceContext;
import com.depaul.cdm.se.yuxi.ini.*;
/**
 *
 * @author apple
 */
public class CustomerMain {
    
    //private static EntityManagerFactory emf;
       @PersistenceContext(unitName = "jpa-project")
    private static EntityManager em;
      public static void main(String[] args) {
          StartupBean sup=new StartupBean(); 
       //  em=sup.getEntityManager();
        CustomerMain main = new CustomerMain();
       // emf = Persistence.createEntityManagerFactory("jpa-project");
       // em = emf.createEntityManager();
       main.createCustomer();
       // main.findEntity();
       // main.updateExample();
       // main.deleteExample();
      //  em.close();
       // emf.close();
    }
     private void createCustomer() {
        Customer customer = new Customer();
        customer.setAge(12);
        customer.setLastName("YU");
        customer.setFirstName("XI");
        customer.setGender("Female");
       
       
        
        //EntityTransaction tx = em.getTransaction();
        //tx.begin();
        em.persist(customer);
       // tx.commit();
       
    }
    
//    /*private void updateExample() {
//        EntityTransaction tx = em.getTransaction();
//        tx.begin();
//        Book book = em.find(Book.class, new Long(1));
//        System.out.println(book);
//        book.setTitle(book.getTitle() + " 2nd edition");
//        tx.commit();
//        
//        List<Book> books = 
//                em.createNamedQuery("findAllBooks").getResultList();
//        System.out.println(books.get(0));
//    }
//    
//    private void deleteExample() {
//        EntityTransaction tx = em.getTransaction();
//        tx.begin();
//        Book book = em.find(Book.class, new Long(1));
//        System.out.println(book);
//        em.remove(book);
//        tx.commit();
//        
//        List<Book> books = 
//                em.createNamedQuery("findAllBooks").getResultList();
//        System.out.print("After delete number of rows remaining is: ");
//        System.out.println(books.size());
//    }
//    
//    private void findEntity() {
//        
//        // 2a.  Find the entity via primary key, auto configured
//        Book book = em.find(Book.class, new Long(1));
//        
//        // 2b.  Find via query, manually configured, see Book class definition
//        List<Book> books = 
//                em.createNamedQuery("findAllBooks").getResultList();
//        
//    }
//    
//    private void deleteEntity() {
//        EntityTransaction tx = em.getTransaction();
//        tx.begin();
//        Book book = em.find(Book.class, new Long(1));
//        System.out.println(book);
//        em.remove(book);
//        tx.commit();
//        
//        List<Book> books = 
//                em.createNamedQuery("findAllBooks").getResultList();
//        System.out.print("After delete number of rows remaining is: ");
//        System.out.println(books.size());
//    }
//    */

}
