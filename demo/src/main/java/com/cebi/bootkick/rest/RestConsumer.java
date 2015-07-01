package com.cebi.bootkick.rest;

import java.util.concurrent.Future;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.cebi.bootkick.model.Quote;

@Service
public class RestConsumer {

	Logger logger = LoggerFactory.getLogger(RestConsumer.class);

	@Cacheable("random")
	public Quote consume() {
		String url = "http://localhost:9000/rest/random";
		try {
			return new RestTemplate().getForObject(url, Quote.class);
		} catch (Exception e) {
			logger.error("Error calling " + url, e);
			Quote q = new Quote();
			q.setType(e.getMessage());
			return q;
		}
	}

	@Async
	public Future<Quote> consumeAsync() {
		String url = "http://localhost:9000/rest/random";
		try {
			Quote q = new RestTemplate().getForObject(url, Quote.class);
			return new AsyncResult<>(q);
		} catch (Exception e) {
			logger.error("Error calling " + url, e);
			Quote q = new Quote();
			q.setType(e.getMessage());
			return new AsyncResult<>(q);
		}
	}
}
