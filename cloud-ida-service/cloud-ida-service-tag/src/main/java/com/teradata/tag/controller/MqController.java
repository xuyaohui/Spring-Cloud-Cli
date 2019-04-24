package com.teradata.tag.controller;

import com.teradata.tag.mq.HelloSender;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import org.springframework.amqp.rabbit.support.CorrelationData;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.UUID;

/**
 * @author xuyaohui
 * @date 2018/7/27 0027 下午 2:45
 */

//@RestController
public class MqController {

    //@Resource
    //private AmqpTemplate rabbitTemplate1;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private HelloSender helloSender;



    @RequestMapping("/direct")
    public String direct(String p) {
        //rabbitTemplate.convertAndSend("hello1","test");
        helloSender.send();
        return "success";
    }


    @RequestMapping("/directAck")
    public String directAck(String p) {
        System.out.println("接收： "+UUID.randomUUID().toString());
        try{
            CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());

            rabbitTemplate.convertAndSend("DIRECT_EXCHANGE", "DIRECT_ROUTING_KEY", p, correlationData);

        }catch (Exception e){
            e.printStackTrace();
        }
      return "send success";
    }
}
