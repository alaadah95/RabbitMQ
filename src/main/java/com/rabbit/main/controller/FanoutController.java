package com.rabbit.main.controller;

import java.time.LocalDateTime;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rabbit.main.model.QueueObject;

@RestController
public class FanoutController {
	@Autowired
	private AmqpTemplate fanoutExchange;

	@PostMapping("/fanout")
	public ResponseEntity<?> sendMessageWithFanoutExchange() {
		QueueObject object = new QueueObject("fanout", LocalDateTime.now());
		fanoutExchange.convertAndSend(object);
		return ResponseEntity.ok(true);
	}
}