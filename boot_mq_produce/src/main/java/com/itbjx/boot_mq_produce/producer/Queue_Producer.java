package com.itbjx.boot_mq_produce.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.jms.Queue;
import java.util.UUID;

@Component
public class Queue_Producer {

	@Autowired
	private JmsMessagingTemplate jmsMessagingTemplate;
	@Autowired
	private Queue queue;

	public void productMsg(){
		jmsMessagingTemplate.convertAndSend(queue,"---:"+ UUID.randomUUID().toString().substring(0,6));
		System.out.println("------消息发送完成-----");
	}

	@Scheduled(fixedDelay = 3000)
	public void scheduledSend(){
		jmsMessagingTemplate.convertAndSend(queue,"---scheduledSend:"+ UUID.randomUUID().toString().substring(0,6));
		System.out.println("scheduledSend  send ok----------");
	}
}
