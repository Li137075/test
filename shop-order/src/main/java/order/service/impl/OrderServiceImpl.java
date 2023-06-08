package order.service.impl;

import domain.Order;
import order.mapper.OrderMapper;
import order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderMapper orderMapper;

    @Override
    public void createrOrder(Order order) {
        orderMapper.createrOrder(order);
    }
}
