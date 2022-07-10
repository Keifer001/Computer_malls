package com.example.test01.mapper;

import com.example.test01.entity.Address;
import com.example.test01.entity.Cart;
import com.example.test01.vo.CartVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Keifer
 * @creat 2022-01-27-10:02
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class CartMapperTests {
    @Autowired
    private CartMapper cartMapper;
    @Test
    public void insert(){
        Cart cart=new Cart();
        cart.setUid(34);
        cart.setNum(3);
        cart.setPid(10000001);
        cart.setPrice(5000L);
        Integer rows=cartMapper.insert(cart);
        System.out.println(rows);
    };
    @Test
    public void updateNumByCid(){
        Integer rows=cartMapper.updateNumByCid(1,5,"管理员",new Date());
        System.out.println(rows);
    }
    @Test
    public void findByUidAndPid(){
        Cart cart=cartMapper.findByUidAndPid(34,10000001);
        System.out.println(cart);
    }
    @Test
    public void findVOByUid(){
        List<CartVO> cartVOS=cartMapper.findVOByUid(34);
        System.out.println(cartVOS);
    }
    @Test
    public void findByCid(){
        System.out.println(cartMapper.findByCid(1));
    };
    @Test
    public void findVOByCids(){
        Integer arr[]=new Integer[]{1,2,3,4,5};
     List<CartVO> cartVOS=cartMapper.findVOByCids(arr);
        System.out.println(cartVOS);
    }
}

