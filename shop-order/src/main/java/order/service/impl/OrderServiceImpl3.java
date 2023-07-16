package order.service.impl;

import com.alibaba.fastjson.JSON;
import domain.Order;
import domain.Product;
import lombok.extern.slf4j.Slf4j;
import order.mapper.OrderMapper;
import order.service.OrderService;
import order.service.ProductService;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


//这个代码是用来测试seta组件的
@Slf4j
//@Service
public class OrderServiceImpl3 implements OrderService{
    @Autowired
    private OrderMapper orderMapper;

    //下面这个注入的是加了feign的一个接口 能够达到访问远程服务 就像是访问本地方法一样的效果
    @Autowired
    private ProductService productService;

    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    public Order createrOrder(Integer pid) {
        Product product=productService.findByPid(pid);

        log.info("接收到{}号商品的下单请求，接下来调用商品微服务查询此商品信息",pid);

        //下单（创建订单）这里是利用已经有的信息 组装出一个订单的类
        Order order=new Order();

        //下面是用户的一些属性,这里为了简便 直接进行模拟了
        order.setUid(1);
        order.setUsername("测试用户");

        //下面是商品的一些信息
        order.setPid(pid);
        order.setPname(product.getPname());
        order.setPprice(product.getPprice());

        //下面是产品的数量 这里为了简单也直接模拟了
        order.setNumber(1);

        orderMapper.createrOrder(order);

        log.info("创建订单成功，订单信息为{}", JSON.toJSONString(order));


        //下面这一行代码指的是向rocketmq消息队列中放入消息
        //向mq中投递一个下单成功的消息
        //参数一：指定topic
        //参数二：指定消息体
        rocketMQTemplate.convertAndSend("order-topic",order);

        return order;
    }

    @Override
    public void deleteOrder(Integer oid) {
        orderMapper.deleteOrder(oid);
    }

    @Override
    public void createrOrder(Order order) {
        orderMapper.createrOrder(order);
    }
}
