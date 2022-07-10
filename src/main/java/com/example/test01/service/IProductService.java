package com.example.test01.service;

import com.example.test01.entity.Address;
import com.example.test01.entity.Product;

import java.util.List;

/**
 * @author Keifer
 * @creat 2022-02-18-9:31
 */
/*处理商品数据的业务层接口*/
public interface IProductService {
    /**
     * 查询热销商品的前四名
     * @return 热销商品的前四名的集合
     */
  List<Product> findHotList();

    /**
     * 根据商品id返回商品详情数据
     * @param id 商品id
     * @return 匹配的商品详情，如果没有匹配的数据则返回null
     */
  Product findById(Integer id);
}
