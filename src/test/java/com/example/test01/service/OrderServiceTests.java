package com.example.test01.service;

import com.example.test01.entity.Address;
import com.example.test01.entity.Order;
import com.example.test01.service.ex.ServiceException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author Keifer
 * @creat 2022-01-27-11:14
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderServiceTests {
    @Autowired
    private IOrderService orderService;
    @Test
    public void create() {
        try {
            Integer aid = 37;
            Integer[] cids = {4, 5, 6, 7};
            Integer uid = 34;
            String username = "订单管理员";
            Order order = orderService.create(cids, uid, aid, username);
            System.out.println(order);
        } catch (ServiceException e) {
            System.out.println(e.getClass().getSimpleName());
            System.out.println(e.getMessage());
        }
    }
}

