package com.itbjx.boot_mq_topic_consummer.consumer;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.TextMessage;

@Component
public class Topic_Consumer {

	@JmsListener(destination = "${mytopic}")
	public void receviceMsg(TextMessage textMessage) throws JMSException {
		System.out.println("消费者接受到的主题消息是："+textMessage.getText());
	}

}
