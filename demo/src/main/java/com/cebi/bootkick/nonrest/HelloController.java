package com.cebi.bootkick.nonrest;

import java.util.Random;
import java.util.concurrent.Future;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cebi.bootkick.model.DemoModel;
import com.cebi.bootkick.model.Quote;
import com.cebi.bootkick.rest.RestConsumer;

@Controller
@RequestMapping("/hello")
public class HelloController {

	@Autowired
	private RestConsumer restConsumer;

	@Value("${deneme}")
	private String deneme;

	@RequestMapping(method = RequestMethod.GET)
	public @ResponseBody DemoModel sayHello(
			@RequestParam(value = "name", required = false, defaultValue = "Caner") String name) {
		DemoModel model = new DemoModel();
		model.setId(new Random().nextLong());
		model.setName("Hello " + name + " " + deneme);
		return model;
	}

	@RequestMapping("/hi")
	public @ResponseBody DemoModel rest(
			@RequestParam(value = "name", required = false, defaultValue = "Caner") String name) {
		DemoModel model = new DemoModel();
		model.setId(new Random().nextLong());
		model.setName("Hi " + name);
		return model;
	}

	@RequestMapping("/restconsume")
	public @ResponseBody Quote consumeRest() {
		Quote quote = restConsumer.consume();
		return quote;
	}

	@RequestMapping("/restconsume_async")
	public @ResponseBody Quote consumeRestAsync() throws Exception {
		Future<Quote> futureQuote = restConsumer.consumeAsync();

		// Wait until done
		while (!futureQuote.isDone()) {
			Thread.sleep(10); // 10-millisecond pause between each check
		}

		Quote quote = futureQuote.get();

		return quote;
	}

}
