package com.example.test01.service.impl;

import com.example.test01.entity.Address;
import com.example.test01.entity.Order;
import com.example.test01.entity.OrderItem;
import com.example.test01.mapper.AddressMapper;
import com.example.test01.mapper.OrderMapper;
import com.example.test01.service.IAddressService;
import com.example.test01.service.ICartService;
import com.example.test01.service.IDistrictService;
import com.example.test01.service.IOrderService;
import com.example.test01.service.ex.*;
import com.example.test01.vo.CartVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.concurrent.locks.ReadWriteLock;

/**
 * @author Keifer
 * @creat 2022-02-18-9:43
 */
@Service
public class OrderServiceImpl implements IOrderService {
    @Autowired
    IAddressService addressService;
    @Autowired
    OrderMapper orderMapper;
    @Autowired
    ICartService cartService;
    @Override
    public Order create(Integer[] cids, Integer uid, Integer aid, String username) {
        //获取订单商品项
        List<CartVO> list=cartService.getVOByCids(cids,uid);
        //获取地址
        Address address=addressService.getByAid(aid,uid);
        Order order=new Order();
        //用户id
        order.setUid(uid);
        //地址信息
        order.setRecvName(address.getName());
        order.setRecvPhone(address.getPhone());
        order.setRecvProvince(address.getProvinceName());
        order.setRecvCity(address.getCityName());
        order.setRecvArea(address.getAreaName());
        order.setRecvAddress(address.getAddress());
        //总价
        long totalPrice=0;
        for(CartVO cartVO:list){
           totalPrice=totalPrice+cartVO.getRealPrice()*cartVO.getNum();
        }
        order.setTotalPrice(totalPrice);
        //状态
        order.setStatus(0);
        order.setOrderTime(new Date());
        //四项日志
        order.setCreatedTime(new Date());
        order.setCreatedUser(username);
        order.setModifiedTime(new Date());
        order.setModifiedUser(username);
        //插入订单
        Integer rows=orderMapper.insertOrder(order);
        if(rows != 1){
            throw new InsertException("插入数据时出现未知异常");
        }
        //遍历查出来的商品列表，循环插入商品数据
        for(CartVO cartVO:list){
            //创建订单商品数据
            OrderItem orderItem=new OrderItem();
            //补全数据oid
            orderItem.setOid(order.getOid());
            // 补全数据：pid, title, image, price, num
            orderItem.setPid(cartVO.getPid());
            orderItem.setTitle(cartVO.getTitle());
            orderItem.setImage(cartVO.getImage());
            orderItem.setPrice(cartVO.getRealPrice());
            orderItem.setNum(cartVO.getNum());
            //四项日志
            orderItem.setCreatedUser(username);
            orderItem.setCreatedTime(new Date());
            orderItem.setModifiedTime(new Date());
            orderItem.setModifiedUser(username);
            //插入商品数据
            Integer rows2=orderMapper.insertOrderItem(orderItem);
            if(rows2 != 1){
                throw new InsertException("插入购物车商品数据时出现未知的异常");
            }
        }
        return order;
    }
}
