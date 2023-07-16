package order.service;

import domain.Order;

public interface OrderService {
    //创建订单
    void createrOrder(Order order);

    void deleteOrder(Integer oid);


}
