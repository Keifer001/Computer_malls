package com.example.test01.service;

import com.example.test01.entity.Address;

import java.util.List;

/**
 * @author Keifer
 * @creat 2022-02-18-9:31
 */
/*收获地址业务层接口*/
public interface IAddressService {
    /**
     * 添加收货地址
     * @param uid 用户id用于存到数据库中
     * @param username 用户名用于更新数据库的修改人
     * @param address 地址信息
     */
    void addNewAddress(Integer uid, String username, Address address);
    /**
     * 根据用户的id获取用户的收货地址信息
     * @param uid 用户id
     * @return 用户的收货地址信息
     */
    List<Address> getByUid(Integer uid);

    /**
     * 设置用户的某条地址为默认地址
     * @param uid 用户id
     * @param aid 地址id
     * @param username 修改者
     */
    void setDefault(Integer uid,Integer aid,String username);

    /**
     * 删除收货地址
     * @param uid 若删除的是默认地址，则查询该用户最新加入的收货地址并设置为默认
     * @param aid 收货地址id
     * @param username 当前修改者
     */
    void deleteAddress(Integer uid,Integer aid,String username);

    /**
     * 根据地址id获取到地址信息
     * @param aid 地址id
     * @param uid 用户id 判断是否非法访问
     * @return 地址信息
     */
    Address getByAid(Integer aid,Integer uid);
}
