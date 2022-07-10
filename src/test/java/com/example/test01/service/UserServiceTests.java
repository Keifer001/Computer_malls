package com.example.test01.service;

import com.example.test01.entity.User;
import com.example.test01.mapper.UserMapper;
import com.example.test01.service.ex.ServiceException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author Keifer
 * @creat 2022-01-27-11:14
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTests {
    @Autowired
    private IUserService userService;
    @Test
    public void reg(){
        try {
            User user=new User();
            user.setUsername("Jeff001");
            user.setPassword("123456");
            userService.reg(user);
            System.out.println("注册成功");
        } catch (Exception e) {
            System.out.println("注册失败"+e.getClass().getSimpleName());
            System.out.println(e.getMessage());
        }
    }
    @Test
    public void login(){
        try {
            String username="qwe";
            String password="123";
            User user=userService.login(username,password);
            System.out.println("登录成功"+user);
        } catch (ServiceException e) {
            System.out.println("登录失败!"+e.getClass().getSimpleName()+e.getMessage());
        }
    }
    @Test
    public void changePassword(){
        userService.changePassword(34,"管理员","123","321");
    }
    @Test
    public void getByUid(){
        System.err.println(userService.getByUid(34));
    }
    @Test
    public void changeInfo(){
        User user=new User();
        user.setPhone("15260826028");
        user.setEmail("116967@qq.com");
        user.setGender(0);
        userService.changeInfo(33,"管理员",user);
    }
    @Test
    public void changeAvatar(){
        userService.changeAvatar(32,"desk/upavatar","管理员");
    }
}
