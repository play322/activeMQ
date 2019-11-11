package com.itbjx.activemq.topic;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.io.IOException;

public class JmsConsumer_topic {
	// linux 上部署的activemq 的 IP 地址 + activemq 的端口号，用自己的需要改动
	public static final String ACTIVEMQ_URL="tcp://192.168.179.128:61616";
	//主题的名称
	public static final String TOPIC_NAME="topic01";

	public static void main(String[] args) throws JMSException, IOException {
		System.out.println("我是1号消费者");
		//1,创建连接工厂
		ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory(ACTIVEMQ_URL);
		//2,创建连接,并启动访问
		Connection connection = factory.createConnection();
		connection.start();
		//3,创建会话session
		//有两个参数，第一个是事务，第二个叫签收
		Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		//4,创建目的地：destination，具体是队列queue和主题topic
		Topic topic = session.createTopic(TOPIC_NAME);
		//5，创建消息消费者
		MessageConsumer consumer = session.createConsumer(topic);
				// 通过监听的方式来消费消息
		consumer.setMessageListener((message -> {
			if (message!=null && message instanceof TextMessage){
				try {
					TextMessage receive = (TextMessage) message;
					System.out.println("接收到的消息是--》"+receive.getText());
				} catch (JMSException e) {
					e.printStackTrace();
				}
			}
		}));
		// 保证控制台不灭  不然activemq 还没连上就关掉了连接
		System.in.read();
		consumer.close();
		session.close();
		connection.close();
	}
}
