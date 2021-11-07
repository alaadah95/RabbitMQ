package com.rabbit.main;

import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootApplication
public class RabbitMqApplication {

	@Value("${spring.rabbitmq.host}")
	private String HOST;

	@Value("${spring.rabbitmq.port}")
	private int PORT;

	@Value("${spring.rabbitmq.virtual-host}")
	private String VIRTUAL_HOST;

	@Value("${spring.rabbitmq.username}")
	private String USERNAME;

	@Value("${spring.rabbitmq.password}")
	private String PASSWORD;

	public static void main(String[] args) {
		SpringApplication.run(RabbitMqApplication.class, args);
	}

	@Bean
	public ConnectionFactory connectionFactory() {
		CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
		connectionFactory.setUsername(USERNAME);
		connectionFactory.setPassword(PASSWORD);
		connectionFactory.setPort(PORT);
		connectionFactory.setHost(HOST);
		connectionFactory.setVirtualHost(VIRTUAL_HOST);

		return connectionFactory;
	}

	@Bean
	public SimpleRabbitListenerContainerFactory listenerContainerFactory() {
		SimpleRabbitListenerContainerFactory containerFactory = new SimpleRabbitListenerContainerFactory();
		containerFactory.setConnectionFactory(connectionFactory());
		containerFactory.setMessageConverter(messageConverter());
		containerFactory.setMaxConcurrentConsumers(10);
		containerFactory.setConcurrentConsumers(5);
		containerFactory.setAutoStartup(true);
		containerFactory.setPrefetchCount(10);
		/*
		 * containerFactory.setAdviceChain(RetryInterceptorBuilder.stateless()
		 * .maxAttempts(3) .recoverer(new RejectAndDontRequeueRecoverer()) .build());
		 */

		// false ==> If there is a problem, don't re-queue. send them to DLX.
		 containerFactory.setDefaultRequeueRejected(false);

		return containerFactory;
	}

	@Bean
	public MessageConverter messageConverter() {
		ObjectMapper objectMapper = new ObjectMapper().findAndRegisterModules();

		return new Jackson2JsonMessageConverter(objectMapper);
	}

}
