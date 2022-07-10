package com.example.test01.mapper;

import com.example.test01.entity.Cart;
import com.example.test01.vo.CartVO;

import java.util.Date;
import java.util.List;

/**
 * @author Keifer
 * @creat 2022-03-03-19:12
 */
public interface CartMapper {
    /**
     * 插入购物车数据
     * @param cart 购物车数据
     * @return 受影响的行数
     */
    Integer insert(Cart cart);
    /**
     *如果之前有查如果该 更新购物车某件商品的数量
     * @param cid 购物车数据id
     * @param num 更新的数量
     * @param modifiedUser 修改者
     * @param modifiedTime 修改时间
     * @return 受影响的行数
     */
    Integer updateNumByCid(Integer cid, Integer num, String modifiedUser, Date modifiedTime);
    /**
     * 根据用户的id和商品的id来查询购物车中的数据 以便于以及有的商品再加入的话直接在数量上增加就好了
     * @param uid 用户id
     * @param pid 商品id
     * @return
     */
    Cart findByUidAndPid(Integer uid,Integer pid);

    /**
     * 根据Uid查询购物车数据
     * @param uid 用户id
     * @return 购物车数据
     */
    List<CartVO> findVOByUid(Integer uid);

    /**
     * 查询当前登录用户是否与要操作的uid一致,查询当前商品是否存在
     * @param cid
     * @return
     */
    Cart findByCid(Integer cid);

    /**
     * 根据若干个购物车数据id查询详情的列表
     * @param cids 若干个购物车数据id
     * @return 匹配的购物车数据详情的列表
     */
    List<CartVO> findVOByCids(Integer[] cids);
}
