package com.rabbit.main.config;

import javax.annotation.PostConstruct;

import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DefaultExchangeConfig {

	@Autowired
	private AmqpAdmin amqpAdmin;

	@Value("${rabbitmq.default.queue}")
	private String defaultQueueName;

	@PostConstruct
	public void init() {

		Queue queue = new Queue(defaultQueueName, false, false, false);
		Queue queue2 = new Queue(defaultQueueName+"2", false, false, false);
		amqpAdmin.declareQueue(queue);
		amqpAdmin.declareQueue(queue2);
	}

	@Bean
	public AmqpTemplate defaultExchange(ConnectionFactory connectionFactory, MessageConverter messageConverter) {
		RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
		rabbitTemplate.setMessageConverter(messageConverter);
		//do not set the exchange .
		rabbitTemplate.setRoutingKey(defaultQueueName);
		return rabbitTemplate;
	}
}
