package com.cebi.bootkick.jms.sender;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
public class QueueSender {

    @Autowired
    private JmsTemplate jmsTemplate;

    public void pushMyQueue(String content) {
        jmsTemplate.convertAndSend("myQueue", content);
    }

    public void pushQueue1(String content) {
        jmsTemplate.convertAndSend("queue1", content);
    }

}
