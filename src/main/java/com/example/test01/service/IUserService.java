package com.example.test01.service;

import com.example.test01.entity.Address;
import com.example.test01.entity.User;

import java.util.List;

/**
 * @author Keifer
 * @creat 2022-01-27-10:41
 */
public interface IUserService {
    /**
     * 用户注册
     * @param user 用户数据
     */
    void reg(User user);

    /**
     * 用户登录
     * @param username 用户名
     * @param password 密码
     * @return 登陆成功的用户数据
     */
    User login(String username,String password);

    /**
     * 修改密码
     * @param uid 当前登录用户的uid
     * @param username 用户名
     * @param oldPassword 原密码
     * @param newPassword 新密码
     */
    void changePassword(Integer uid,
                        String username,
                        String oldPassword,
                        String newPassword);

    /**
     * 根据用户的uid查询用户的数据
     * @param uid 用户uid
     * @return 用户数据
     */
    User getByUid(Integer uid);

    /**
     * 修改用户资料
     * @param uid 当前登录用户uid
     * @param username 当前登录的用户名
     * @param user 用户的新数据
     */
    void changeInfo(Integer uid,String username,User user);

    /**
     * 修改用户头像
     * @param uid 用户id
     * @param avatar 用户头像的路径
     * @param username 用户的名称 用于给修改者赋值 修改时间持久层已经new
     */
    void changeAvatar(Integer uid,String avatar,String username);

}
