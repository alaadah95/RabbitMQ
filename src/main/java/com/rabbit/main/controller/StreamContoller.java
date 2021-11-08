package com.rabbit.main.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rabbit.main.model.Employee;
import com.rabbit.main.streams.source.Notification;
import com.rabbit.main.streams.source.Publisher;

@RestController
@RequestMapping
public class StreamContoller {
	@Autowired
	private Publisher source;
	@Autowired
	private Notification notification;

	@PostMapping("/order")
	public void publishMessage(@RequestBody Employee body) {
		source.orders().send(MessageBuilder.withPayload(body).build());
	}

	@PostMapping("/notify")
	public void notification(@RequestBody Employee body) {
		notification.notification().send(MessageBuilder.withPayload(body).build());
	}
}
