package com.itbjx.activemq.queue;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.io.IOException;

public class JmsConsumer {

	// linux 上部署的activemq 的 IP 地址 + activemq 的端口号，用自己的需要改动
	public static final String ACTIVEMQ_URL="tcp://192.168.179.128:61616";
	//public static final String ACTIVEMQ_URL="tcp://localhost:61616";
	//队列的名称
	public static final String QUEUE_NAME="transport";

	public static void main(String[] args) throws JMSException, IOException {
		System.out.println("我是2号消费者");
		//1,创建连接工厂
		ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory(ACTIVEMQ_URL);
		//2,创建连接,并启动访问
		Connection connection = factory.createConnection();
		connection.start();
		//3,创建会话session
		//有两个参数，第一个是事务，第二个叫签收
		Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		//4,创建目的地：destination，具体是队列queue和主题topic
		//Queue queue = session.createQueue();
		Destination queue = session.createQueue(QUEUE_NAME);
		//5，创建消息消费者
		MessageConsumer consumer = session.createConsumer(queue);
		/*
		   一，同步阻塞方式：
		//6,从队列取消息
		while (true){
			//没有时间限制（消息接受完后，一直等待）
			//TextMessage message = (TextMessage) producer.receive();
			//有时间限制（消费者消息接受完后多长时间后离开）
			TextMessage message = (TextMessage) producer.receive(4000L);
			if (message!=null){
				System.out.println("接收到的消息是--》"+message.getText());
			}else {
				break;
			}
		}
		producer.close();
		session.close();
		connection.close();*/

		// 通过监听的方式来消费消息
		// 通过异步非阻塞的方式消费消息
		// 通过messageConsumer 的setMessageListener 注册一个监听器，
		// 当有消息发送来时，系统自动调用MessageListener 的 onMessage 方法处理消息

			consumer.setMessageListener(new MessageListener() {
				@Override
				public void onMessage(Message message) {
					if (message!=null && message instanceof TextMessage){
						try {
							TextMessage receive = (TextMessage) message;
							System.out.println("接收到的消息是--》"+receive.getText());
						} catch (JMSException e) {
							e.printStackTrace();
						}
					}
				}
			});
		// 保证控制台不灭  不然activemq 还没连上就关掉了连接
			System.in.read();
			consumer.close();
			session.close();
			connection.close();
	}
}
