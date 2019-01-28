package com.wondertek.self.spring.rocketmq;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.message.MessageExt;

import java.util.List;

/**
 * @Author zbc
 * @Date 23:09-2019/1/11
 */
public class Consumer {

    private static final String DEFAULT_HOST = "192.168.115.131:9876";

    public static void main(String[] args) {

        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("group-consumer-test");

        consumer.setNamesrvAddr(DEFAULT_HOST);
        try {
            consumer.subscribe("TopicTest","*");
            consumer.registerMessageListener(new MessageListenerConcurrently() {
                @Override
                public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
                    System.out.printf("%s Receive New Messages: %s %n", Thread.currentThread().getName(), msgs);
                    return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
                }
            });

            consumer.start();

        } catch (MQClientException e) {
            e.printStackTrace();
        }
        System.out.println("consumer started");
    }
}
