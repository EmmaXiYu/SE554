package com.depaul.cdm.se.yuxi.ejb.impl;


import com.depaul.cdm.se.yuxi.ejb.LGreeterBeanLocal;
import javax.ejb.Stateless;

@Stateless
public class LGreeterBean implements LGreeterBeanLocal {

    @Override
    public String greetMe(String name) {
        return "Local hello: " + name;
    }
}
