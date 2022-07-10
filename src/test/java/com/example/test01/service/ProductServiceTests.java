package com.example.test01.service;

import com.example.test01.entity.Address;
import com.example.test01.entity.Product;
import com.example.test01.service.ex.ServiceException;
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
public class ProductServiceTests {
    @Autowired
    private IProductService productService;

    @Test
    public void findHotList() {
        try {
            List<Product>list=productService.findHotList();
            System.out.println("count:"+list.size());
            for (Product product:list){
                System.out.println(product);
            }
        } catch (ServiceException e) {
            System.out.println(e.getMessage());
            System.out.println(e.getClass().getSimpleName());
        }
    }
}