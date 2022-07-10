package com.example.test01.mapper;

import com.example.test01.entity.Product;

import java.util.List;

/**
 * @author Keifer
 * @creat 2022-03-03-8:28
 */
/*处理商品数据的持久层接口*/
public interface ProductMapper {
    /**
     * 查询热销商品前四名
     * @return 热销商品前四名集合
     */
    List<Product> findHotList();
    Product findById(Integer id);
}
