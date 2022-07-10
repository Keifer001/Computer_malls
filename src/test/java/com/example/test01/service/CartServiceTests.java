package com.example.test01.service;

import com.example.test01.entity.Address;
import com.example.test01.service.ex.ServiceException;
import com.example.test01.vo.CartVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @author Keifer
 * @creat 2022-01-27-11:14
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class CartServiceTests {
    @Autowired
    private ICartService cartService;
    @Test
    public void addToCart(){
        cartService.addToCart(3,35,10000001,"管理员");
    }
    @Test
    public void getVOByCids(){
        Integer[] cids={1,2,3,4};
        List<CartVO> list=cartService.getVOByCids(cids,34);
        for(CartVO cartVO:list){
            System.out.println(cartVO);
        }
    }
}
