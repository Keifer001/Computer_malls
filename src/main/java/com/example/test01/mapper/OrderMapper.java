package com.example.test01.mapper;

import com.example.test01.entity.Order;
import com.example.test01.entity.OrderItem;

/**
 * @author Keifer
 * @creat 2022-03-09-15:45
 */
public interface OrderMapper {
    /**
     * 插入订单数据
     * @param order 订单数据
     * @return 影响行数
     */
    Integer insertOrder(Order order);

    /**
     * 插入订单项数据
     * @param orderItem 订单项
     * @return 影响行数
     */
    Integer insertOrderItem(OrderItem orderItem);
}
