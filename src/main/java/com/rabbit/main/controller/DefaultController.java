package com.rabbit.main.controller;

import java.time.LocalDateTime;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rabbit.main.model.QueueObject;
import com.rabbit.main.model.WebSocketChatMessage;

@RestController
public class DefaultController {
	
	@Autowired
	private AmqpTemplate defaultExchange ;
	
	@PostMapping("/default")
    public ResponseEntity<?> sendMessageWithDefaultExchange()
    {
		//message
        QueueObject object = new QueueObject("default", LocalDateTime.now());
        
        defaultExchange.convertAndSend(object);
//      defaultExchange.convertAndSend("defualt-queue2", object);
        return ResponseEntity.ok(true);
    }
}
