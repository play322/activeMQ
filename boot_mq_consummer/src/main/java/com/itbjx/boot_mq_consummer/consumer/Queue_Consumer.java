package com.itbjx.boot_mq_consummer.consumer;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.TextMessage;

@Component
public class Queue_Consumer {

	@JmsListener(destination = "${myqueue}")
	public void receive(TextMessage textMessage) throws JMSException {
		System.out.println("消费者收到的消息是："+textMessage.getText());
	}
}
