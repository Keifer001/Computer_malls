package com.example.test01.controller;

import com.example.test01.entity.Address;
import com.example.test01.entity.Order;
import com.example.test01.service.IAddressService;
import com.example.test01.service.IOrderService;
import com.example.test01.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author Keifer
 * @creat 2022-02-18-11:17
 */
@RestController
@RequestMapping("orders")
public class OrderController extends BaseController {
    @Autowired
    private IOrderService orderService;
    @RequestMapping("create")
    public JsonResult<Order> create(Integer[] cids,Integer aid,HttpSession session){
        Order data=orderService.create(cids,getuidFromSession(session),aid,getUsernameFromSession(session));
        return new JsonResult<Order>(OK,data);
    }
}

