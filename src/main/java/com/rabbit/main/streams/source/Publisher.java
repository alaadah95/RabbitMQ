package com.rabbit.main.streams.source;


import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;
import org.springframework.stereotype.Component;

@Component
public interface Publisher {
	
	//Exchange name (order)
	@Output("orders")
	MessageChannel orders();
	
	
}
