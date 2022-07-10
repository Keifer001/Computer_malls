package com.example.test01.mapper;

import com.example.test01.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.Date;

/**
 * @author Keifer
 * @creat 2022-01-27-9:22
 */
public interface UserMapper {
    /**
     * 插入用户数据
     * @param user 用户数据
     * @return 受影响的行数
     */
    Integer insert(User user);

    /**
     * 根据用户名查询用户的数据
     * @param username 用户名
     * @return 匹配用户的数据，如果没有匹配则返回null
     */

    User findByUsername(String username);

    /**
     * 根据用户的uid来修改密码
     * @param uid 用户的uid
     * @param password 用户新密码
     * @param modifiedUser 表示修改的执行者
     * @param modifiedTime 表示修改数据的时间
     * @return 影响的行数
     */
    Integer updatePasswordByUid(Integer uid,
                                String password,
                                String modifiedUser,
                                Date modifiedTime);

    /**
     * 根据Uid查询用户的数据
     * @param uid 用户的uid
     * @return 如果找到则返回对象，否则返回null
     */
    User findByUid(Integer uid);

    /**
     * 更新用户的数据信息
     * @param user 用户数据
     * @return 返回值为受影响的行数
     */                                          //增删改用户需要提交的数据比较多的话可以选择对象类型
    Integer updateInfoByUid(User user);         //一般增删改返回值为受影响的行数，查的返回值为对象类型，
   //前端和controller层字段手动进行对应 @pathVariable("user")String username 可以理解为强行匹配 @RequestParm("user")String username
    //数据库对应的字段和接口当中的对应@parm("user") String username 如下例子 @里的是自己想自定义的后面的是想匹配的
    /**
     *根据uid来修改用户的数据
     * @param avatar
     * @param uid
     * @param modifiedUser
     * @param modifiedTime
     * @return
     */
    Integer updateAvatarByUid(@Param("avatar") String avatar,
                              @Param("uid") Integer uid,
                              @Param("modifiedUser") String modifiedUser,
                              @Param("modifiedTime") Date modifiedTime);
}
