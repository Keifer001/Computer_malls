package com.example.test01.service.impl;

import com.example.test01.entity.Address;
import com.example.test01.entity.Product;
import com.example.test01.mapper.AddressMapper;
import com.example.test01.mapper.ProductMapper;
import com.example.test01.service.IAddressService;
import com.example.test01.service.IDistrictService;
import com.example.test01.service.IProductService;
import com.example.test01.service.ex.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author Keifer
 * @creat 2022-02-18-9:43
 */
/*处理商品数据的业务层实现类*/
@Service
public class ProductServiceImpl implements IProductService {
    @Autowired
    private ProductMapper productMapper;

    @Override
    public List<Product> findHotList() {
        List<Product>list=productMapper.findHotList();
        for (Product product:list){
            product.setPriority(null);
            product.setCreatedUser(null);
            product.setCreatedTime(null);
            product.setModifiedUser(null);
            product.setModifiedTime(null);
        }
        return list;
    }

    @Override
    public Product findById(Integer id) {
        Product product=productMapper.findById(id);
        if(product==null){
            throw new ProductNotFoundException("该商品详情不存在");
        }
        product.setPriority(null);
        product.setCreatedUser(null);
        product.setCreatedTime(null);
        product.setModifiedUser(null);
        product.setModifiedTime(null);
        return product;
    }
}
