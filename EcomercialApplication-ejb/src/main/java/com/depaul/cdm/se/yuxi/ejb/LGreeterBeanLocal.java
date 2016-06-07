package com.depaul.cdm.se.yuxi.ejb;

import javax.ejb.Local;

@Local
public interface LGreeterBeanLocal {
    public String greetMe(String name);
}
