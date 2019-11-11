package com.itbjx.activemq.topic;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class JmsProducer_topic {


	// linux 上部署的activemq 的 IP 地址 + activemq 的端口号，如果用自己的需要改动
	public static final String ACTIVEMQ_URL="tcp://192.168.179.128:61616";
	//主题的名称
	public static final String TOPIC_NAME="topic01";

	public static void main(String[] args) throws JMSException {
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
		//5，创建消息生产者
		MessageProducer producer = session.createProducer(topic);
		//6,通过消息生产者发送3条消息到队列
		for (int i = 1; i <=3 ; i++) {
			//7,创建消息
			//Message message = session.createTextMessage("msg-->"+i);
			Message message = session.createTextMessage("topic msg-->"+i);
			//8,通过消息生产者发送3条消息到队列
			producer.send(message);
		}
		//9，关闭资源
		producer.close();
		session.close();
		connection.close();

		System.out.println("*************消息发布成功！");
	}

}

