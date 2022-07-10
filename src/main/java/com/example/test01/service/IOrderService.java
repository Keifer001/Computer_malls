package com.example.test01.service;

import com.example.test01.entity.Address;
import com.example.test01.entity.Order;

import java.util.List;

/**
 * @author Keifer
 * @creat 2022-02-18-9:31
 */
public interface IOrderService {
    Order create(Integer[] cids, Integer uid, Integer aid, String username);
}
