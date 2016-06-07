package com.depaul.edu.ecomercialapplication.remote;

import java.util.concurrent.Future;
import javax.ejb.Remote;

@Remote
public interface GreeterBeanRemote {

    Future<String> greetMe(String name);
    
}
