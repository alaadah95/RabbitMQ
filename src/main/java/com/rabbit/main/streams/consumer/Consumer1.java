package com.rabbit.main.streams.consumer;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.stereotype.Component;

import com.rabbit.main.model.Employee;
import com.rabbit.main.streams.source.Publisher;

@Component
public class Consumer1 {
	@StreamListener(target = "orders")
	public void processRegisterEmployees(Employee employee) {
		System.out.println("Employees Registered by Consumer1 " + employee.getEmpName());
	}
}
