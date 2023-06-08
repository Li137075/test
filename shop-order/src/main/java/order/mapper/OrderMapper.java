package order.mapper;


import domain.Order;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
//这里的JpaRepository中的两个泛型 第一个是实体类 第二个是实体类的主键类型
public interface OrderMapper{

    void createrOrder(Order order);
}
