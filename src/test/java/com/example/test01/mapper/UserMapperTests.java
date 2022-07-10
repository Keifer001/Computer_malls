package com.example.test01.mapper;

import com.example.test01.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

/**
 * @author Keifer
 * @creat 2022-01-27-10:02
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class UserMapperTests {
    @Autowired
    private UserMapper userMapper;
    @Test
    public void insert(){
        User user=new User();
        user.setUsername("Tom002");
        user.setPassword("123456");
        Integer rows=userMapper.insert(user);
        System.out.println("影响的行数:"+rows);
    }
    @Test
    public void findByUsername(){
        String username="Tom001";
        User result=userMapper.findByUsername(username);
        System.out.println(result);
    }
    @Test
    public void updatePasswordByUid(){
        userMapper.updatePasswordByUid(32,"321","管理员",new Date());
    };
    @Test
    public void findByUid(){
        System.out.println(userMapper.findByUid(34));
    };
    @Test
    public void updateInfoByUid(){
        User user=new User();
        user.setUid(33);
        user.setGender(1);
        user.setPhone("123456789");
        user.setEmail("123456@qq.com");
        userMapper.updateInfoByUid(user);
    }
    @Test
    public void updateAvatarByUid(){
        userMapper.updateAvatarByUid("/upload/avatar.png",34,"管理员",new Date());
    }
}

