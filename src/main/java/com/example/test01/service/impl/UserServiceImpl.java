package com.example.test01.service.impl;

import com.example.test01.entity.User;
import com.example.test01.mapper.UserMapper;
import com.example.test01.service.IUserService;
import com.example.test01.service.ex.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import sun.security.util.Password;

import javax.jws.soap.SOAPBinding;
import javax.xml.transform.Result;
import java.util.Date;
import java.util.UUID;

/**
 * @author Keifer
 * @creat 2022-01-27-10:45
 */
@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public void reg(User user) {
        String username=user.getUsername();
        User result =userMapper.findByUsername(username);
        if(result!=null){
            throw new UsernameDuplicateException("该用户名已被占用");
        }
        Date date=new Date();
        //md5密码
        String salt= UUID.randomUUID().toString().toUpperCase();
        String md5Password=getMd5Password(user.getPassword(),salt);
        user.setPassword(md5Password);
        //盐值
        user.setSalt(salt);
        //补全数据isDelete(0)
        user.setIsDelete(0);
        //四个日志属性
        user.setCreatedUser(username);
        user.setModifiedUser(username);
        user.setCreatedTime(date);
        user.setModifiedTime(date);
        Integer rows=userMapper.insert(user);
        if(rows!=1){
            throw new InsertException("注册出现未知错误请联系管理员");
        }
    }

    @Override
    public User login(String username, String password) {
        User result=userMapper.findByUsername(username);
        if(result==null || result.getIsDelete()==1){
            throw new UserNotFoundException("该用户不存在");
        }
        String salt=result.getSalt();
        String md5Password=getMd5Password(password,salt);
        if(!md5Password.equals(result.getPassword())){
            throw new PasswordNotMatchException("用户密码错误");
        }
        User user=new User();
        user.setUid(result.getUid());
        user.setUsername(result.getUsername());
        user.setAvatar(result.getAvatar());
        return user;
    }

    @Override
    public void changePassword(Integer uid, String username, String oldPassword, String newPassword) {
        User result= userMapper.findByUid(uid);
        if(result==null ||result.getIsDelete()==1){
            throw new UserNotFoundException("用户数据不存在");
        }
        //原始密码与新密码进行比较
        String oldMd5Password=getMd5Password(oldPassword,result.getSalt());
        if(!result.getPassword().equals(oldMd5Password)){
            throw new PasswordNotMatchException("密码有误");
        }
        //将新的密码设置到数据库中，将新的密码进行加密再去更新
        String newMd5Password=getMd5Password(newPassword,result.getSalt());
        Integer rows=userMapper.updatePasswordByUid(uid,newMd5Password,username,new Date());
        if(rows!=1){
            throw new UpdateException("更新数据产生了未知的异常");
        }
    }

    @Override   //根据uid得到未修改用户资料时的数据在用户点击个人资料时填入,
    public User getByUid(Integer uid) {
        User result= userMapper.findByUid(uid);
        if(result==null || result.getIsDelete()==1){
            throw  new UserNotFoundException("用户数据不存在");
        }
        User user=new User();
        user.setUsername(result.getUsername());
        user.setGender(result.getGender());
        user.setEmail(result.getEmail());
        user.setPhone(result.getPhone());
        return user;
    }                         //增删改一般没有返回值 注册查询一般有返回值，返回对象类型

    @Override
    public void changeInfo(Integer uid, String username, User user) {
        User result= userMapper.findByUid(uid);
        if(result==null||result.getIsDelete()==1){
            throw new UserNotFoundException("用户数据不存在");
        }
        user.setUid(uid);
        user.setModifiedUser(username);
        user.setModifiedTime(new Date());
        Integer rows=userMapper.updateInfoByUid(user);
        if(rows!=1){
            throw new UpdateException("更新数据时出现了未知的异常");
        }
    }

    @Override
    public void changeAvatar(Integer uid, String avatar, String username) {
        User result=userMapper.findByUid(uid);
        if(result==null||result.getIsDelete()==1){
            throw  new UserNotFoundException("用户数据不存在");
        }
        Date modifiedTime=new Date();
        Integer rows=userMapper.updateAvatarByUid(avatar,uid,username,modifiedTime);
        if(rows!=1){
            throw  new UpdateException("更新用户头像时出现未知的异常");
        }
    }

    //密码加密
    public String getMd5Password(String password,String salt) {
        for (int i = 0; i < 3; i++) {
            password = DigestUtils.md5DigestAsHex((salt + password + salt).getBytes()).toUpperCase();
        }
        return password;
    }
}
