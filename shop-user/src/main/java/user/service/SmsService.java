package user.service;

import domain.Order;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Service;

//这里是一个监听的类 实现了一个监听的方法，当消息队列中有消息时 就会运行这个监听的方法

@Slf4j
@Service
//consumerGroup  消费者组名  topic  要消费的主题
@RocketMQMessageListener(consumerGroup = "shop-user",topic = "order-topic")
public class SmsService implements RocketMQListener<Order> {


    //下面这行代码是将消息队列中的消息  在这里进行处理
    //user作为消费者 进行消费order提供消息 的一个逻辑的编写
    @Override
    public void onMessage(Order message) {
        log.info("接受到了一个订单信息{}，接下来就可以发送短信通知了",message);
    }
}
