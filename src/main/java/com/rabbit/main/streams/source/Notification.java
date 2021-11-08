package com.rabbit.main.streams.source;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface Notification {
	//Exchange name (order)
		@Output("notification")
		MessageChannel notification();
}
