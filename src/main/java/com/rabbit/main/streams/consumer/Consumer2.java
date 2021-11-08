package com.rabbit.main.streams.consumer;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.stereotype.Component;

import com.rabbit.main.model.Employee;

@Component
public class Consumer2 {
	@StreamListener(target = "orders")
	public void processRegisterEmployees(Employee employee) {
		System.out.println("Employees Registered by Consumer2 " + employee.getEmpName());
	}
}
