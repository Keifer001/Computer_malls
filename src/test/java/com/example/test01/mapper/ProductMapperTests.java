package com.example.test01.mapper;

import com.example.test01.entity.Address;
import com.example.test01.entity.Product;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

/**
 * @author Keifer
 * @creat 2022-01-27-10:02
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class ProductMapperTests {
    @Autowired
    private ProductMapper productMapper;
    @Test
    public void findHotList(){
        List<Product>products=productMapper.findHotList();
        System.out.println("count:"+products.size());
        for (Product product:products){
            System.out.println(product);
        }
    }
    @Test
    public void findById(){
        Product product=productMapper.findById(10000017);
        System.out.println(product);
    };
}

