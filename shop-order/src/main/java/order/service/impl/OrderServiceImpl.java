package order.service.impl;

import domain.Order;
import io.seata.spring.annotation.GlobalTransactional;
import order.mapper.OrderMapper;
import order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper orderMapper;


//  下面这个注解@GlobalTransactional是seata中的开启全局事务的注解
//    @GlobalTransactional
    @Override
    public void createrOrder(Order order) {
        orderMapper.createrOrder(order);
    }

    @Override
    public void deleteOrder(Integer oid) {
        orderMapper.deleteOrder(oid);
    }
}
