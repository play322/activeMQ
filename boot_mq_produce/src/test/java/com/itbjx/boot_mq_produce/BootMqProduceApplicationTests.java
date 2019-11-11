package com.itbjx.boot_mq_produce;

import com.itbjx.boot_mq_produce.producer.Queue_Producer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BootMqProduceApplicationTests {

	@Autowired
	private Queue_Producer queue_producer;
	@Test
	void contextLoads() {
		queue_producer.productMsg();
	}

}
