package com.cebi.bootkick.jms.listener;

import com.cebi.bootkick.dao.DemoModelDao;
import com.cebi.bootkick.model.DemoModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class QueueListener {

    private static final Logger logger = LoggerFactory.getLogger(QueueListener.class);

    @Autowired
    private DemoModelDao demoModelDao;

    @JmsListener(destination = "myQueue")
    public void processMyQueue(String content) {

        System.out.println("myQueue" + content);

        DemoModel model = new DemoModel();
        model.setName(content);

        demoModelDao.save(model);

    }

    @JmsListener(destination = "queue1")
    public void processQueue1(String content) {

        System.out.println("queue1" + content);

        logger.info("logger logger log log");

        DemoModel model = new DemoModel();
        model.setName(content);

        demoModelDao.save(model);

    }

}
