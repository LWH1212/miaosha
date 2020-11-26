package com.lwh.mq;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MQConfig {

    public static final String MIAOSHA_QUEUE = "seckill.queue";

    public static final String PAY_QUEUE = "pay_queue";

    public static final String QUEUE = "queue";

    @Bean
    public MessageConverter getMessageConverter(){
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public Queue queue(){
        return new Queue(QUEUE,true);
    }

    @Bean
    public Queue miaosha_queue(){
        return new Queue(MIAOSHA_QUEUE,true);
    }

    @Bean
    public Queue pay_queue(){
        return new Queue(PAY_QUEUE,true);
    }
}
