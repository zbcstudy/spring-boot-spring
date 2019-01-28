package com.wondertek.self.spring.rocketmq;

import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.apache.rocketmq.remoting.exception.RemotingException;

import java.io.UnsupportedEncodingException;

/**
 * rocketMq的发送者
 * @Author zbc
 * @Date 22:57-2019/1/11
 */
public class Producer {

    private static final String DEFAULT_HOST = "192.168.115.131:9876";

    public static void main(String[] args) throws MQClientException, UnsupportedEncodingException, RemotingException, InterruptedException, MQBrokerException {
        DefaultMQProducer producer = new DefaultMQProducer("group-publish-test");
        producer.setNamesrvAddr(DEFAULT_HOST);

        producer.start();

        for (int i = 0; i < 100; i++) {
            Message message = new Message("TopicTest", "TagA",
                    ("hello rocketmq-" + i).getBytes(RemotingHelper.DEFAULT_CHARSET));
            SendResult sendResult = producer.send(message,10000l);
            System.out.printf("%s%n", sendResult);
        }

        producer.shutdown();
    }
}
