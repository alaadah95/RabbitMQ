package com.rabbit.main.config;

import javax.annotation.PostConstruct;

import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OverflowQueue {

	@Autowired
	private AmqpAdmin amqpAdmin;

	@Value("${rabbitmq.overflow.queue}")
	private String QUEUE_NAME;

	Queue createOverflowQueue() {
		return QueueBuilder.durable(QUEUE_NAME).maxLength(2).overflow(QueueBuilder.Overflow.rejectPublish).build();
	}

	@Bean
	public AmqpTemplate defaultOverflowExchange(ConnectionFactory connectionFactory,
			MessageConverter messageConverter) {
		RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
		rabbitTemplate.setMessageConverter(messageConverter);
		rabbitTemplate.setRoutingKey(QUEUE_NAME);
		return rabbitTemplate;
	}

	@PostConstruct
	public void init() {
		amqpAdmin.declareQueue(createOverflowQueue());
	}

}
