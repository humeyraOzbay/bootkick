package com.cebi.bootkick.rest;

import com.cebi.bootkick.dao.DemoModelDao;
import com.cebi.bootkick.jms.sender.QueueSender;
import com.cebi.bootkick.model.DemoModel;
import com.cebi.bootkick.model.Quote;
import com.cebi.bootkick.model.Value;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest")
public class Rest {

    @Autowired
    private DemoModelDao demoModelDao;
    @Autowired
    private QueueSender queueSender;

    @RequestMapping("/liste")
    public @ResponseBody
    Iterable<DemoModel> list() {
        return demoModelDao.findAll();
    }

    @RequestMapping("/bul")
    public @ResponseBody
    List<DemoModel> find(
            @RequestParam(value = "name", required = true) String name) {
        return demoModelDao.findByName(name);
    }

    @RequestMapping("/id/{id}")
    public @ResponseBody
    DemoModel findById(@PathVariable("id") Long id) {
        return demoModelDao.findOne(id);
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public @ResponseBody
    String addPerson(@RequestBody DemoModel model) {
        demoModelDao.save(model);
        return "Object was successfully saved";
    }

    @RequestMapping("/ekle")
    public @ResponseBody
    String add(
            @RequestParam(value = "name", required = false, defaultValue = "Caner") String name) {
        DemoModel model = new DemoModel();
        model.setName(name);
        demoModelDao.save(model);
        return "Eklendi wit id:" + model.getId();
    }

    @RequestMapping("/ekle_async")
    public @ResponseBody
    String addAsync(
            @RequestParam(value = "name", required = false, defaultValue = "Caner") String name) {

        queueSender.pushMyQueue(name + "_MyQueue");

        queueSender.pushQueue1(name + "_Queue1");

        return "2 ayri Queue'ya atildi.";
    }
    
    @RequestMapping("/random")
    public @ResponseBody
    Quote quote() {
        Quote q = new Quote();
        q.setType("aferin");
        Value v = new Value();
        v.setId(new Random().nextLong());
        v.setQuote(UUID.randomUUID().toString());
        q.setValue(v);
        return q;
    }

}
