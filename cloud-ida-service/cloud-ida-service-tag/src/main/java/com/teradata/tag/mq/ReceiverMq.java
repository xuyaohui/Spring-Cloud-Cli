package com.teradata.tag.mq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Date;

/**
 * @author xuyaohui
 * @date 2018/7/27 0027 下午 2:42
 */

//@Component
public class ReceiverMq {

    private static final Logger log = LoggerFactory.getLogger(ReceiverMq.class);


    @RabbitListener(queues = {"hello1"})
    public void process(String hello) {
        System.out.println("Receiver  : " + hello);

    }

    @RabbitListener(queues = {"hello"})
    public void process(String hello,Channel channel, Message message) throws IOException {
        System.out.println("HelloReceiver收到  : " + hello +"收到时间"+new Date());
        try {
            //执行业务逻辑

            //若消费者业务异常，首先执行guava的retry，超出重试次数后，发邮件/短信或其他方式通过人肉的方式解决

            //告诉服务器收到这条消息 已经被我消费了 可以在队列删掉 这样以后就不会再发了 否则消息服务器以为这条消息没处理掉 后续还会在发
            //channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
            System.out.println("receiver success");
        } catch (Exception e) {
            e.printStackTrace();
            //丢弃这条消息
            //channel.basicNack(message.getMessageProperties().getDeliveryTag(), false,false);
            System.out.println("receiver fail");
        }

    }

    /**
     * DIRECT模式.
     *
     * @param message the message
     * @param channel the channel
     * @throws IOException the io exception  这里异常需要处理
     */
    @RabbitListener(queues = {"DIRECT_QUEUE"})
    public void message(Message message, Channel channel) throws IOException {
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), true);
        log.debug("DIRECT "+new String (message.getBody()));
    }
}