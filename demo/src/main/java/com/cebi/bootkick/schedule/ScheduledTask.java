package com.cebi.bootkick.schedule;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTask {

	private static final SimpleDateFormat dateFormat = new SimpleDateFormat(
			"HH:mm:ss");

	@Scheduled(cron = "0 0/1 * 1/1 * ?")
	public void reportCurrentTime() {
		System.out.println("Scheduled by Spring with cron expression. Time is "
				+ dateFormat.format(new Date()));
	}

}
