package com.cebi.bootkick.ws;

import com.cebi.bootkick.jms.sender.QueueSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HelloWsImpl implements HelloWs {

    @Autowired
    private QueueSender queueSender;

    @Override
    public String sayHi(String name) {
        queueSender.pushQueue1("Hi " + name);
        return "Hi " + name;
    }
}
