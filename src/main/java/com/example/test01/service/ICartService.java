package com.example.test01.service;

import com.example.test01.entity.Address;
import com.example.test01.entity.Cart;
import com.example.test01.vo.CartVO;

import java.util.List;

/**
 * @author Keifer
 * @creat 2022-02-18-9:31
 */
/*处理购物车数据业务层接口*/
public interface ICartService {
    /**
     * 将商品添加到购物车中
     * @param amount 新增数量
     * @param uid 用户id
     * @param pid 商品id
     * @param username 用户名
     */
    void addToCart(Integer amount,Integer uid,Integer pid,String username);

    /**
     * 根据uid
     * @param uid
     * @return
     */
    List<CartVO> getVOByUid(Integer uid);
    /**
     *将购物车中某商品的数量加一
     * @param cid 购物车id
     * @param username 用户名
     * @param uid 用户id
     */ /*也可以不要返回值重新加载一下页面，但是数据量会比较大*/
    Integer addNum(Integer cid,String username,Integer uid);

    /**
     * 购物车中的数量减一
     * @param cid 购物车id
     * @param username 用户名
     * @param uid 用户id
     * @return 购物车商品数量
     */
    Integer deleteNum(Integer cid,String username,Integer uid);

    /**
     *
     * @param cids 商品id
     * @param uid 非法访问判断
     * @return 商品详情
     */
    List<CartVO> getVOByCids(Integer[] cids,Integer uid);
}
