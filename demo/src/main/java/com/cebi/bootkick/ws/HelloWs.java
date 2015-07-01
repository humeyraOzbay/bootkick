package com.cebi.bootkick.ws;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService(name = "helloService", portName = "helloPort")
public interface HelloWs {

    @WebMethod
    public String sayHi(String name);
}
