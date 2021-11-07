package com.rabbit.main.controller;

import java.time.LocalDateTime;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rabbit.main.model.QueueObject;

@RestController
public class TestController {

	@Autowired
	AmqpTemplate defaultExchange;

	@PostMapping("/test")
	public ResponseEntity<?> test() {
		QueueObject object = new QueueObject("test", LocalDateTime.now());
		defaultExchange.convertAndSend("fanout-queue-2", object);
		return ResponseEntity.ok(true);
	}
}
