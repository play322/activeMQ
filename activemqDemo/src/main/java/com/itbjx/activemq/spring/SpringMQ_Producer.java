package com.itbjx.activemq.spring;

import org.apache.xbean.spring.context.ClassPathXmlApplicationContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import javax.jms.TextMessage;

@Service
public class SpringMQ_Producer {

	@Autowired
	private JmsTemplate jmsTemplate;

	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		SpringMQ_Producer producer = (SpringMQ_Producer) ctx.getBean("springMQ_Producer");
		producer.jmsTemplate.send((session)-> {
				TextMessage message = session.createTextMessage("spring与activemq的整合case3333。。。。。");
				return message;
		});
		System.out.println("------消息发送完成------");
	}
}
