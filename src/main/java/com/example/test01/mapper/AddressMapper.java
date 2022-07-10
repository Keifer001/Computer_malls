package com.example.test01.mapper;

import com.example.test01.entity.Address;
import org.apache.ibatis.annotations.Param;

import java.rmi.activation.ActivationID;
import java.util.Date;
import java.util.List;

/**
 * @author Keifer
 * @creat 2022-02-17-16:58
 */
/*收获地址持久层的接口*/
public interface AddressMapper {
    /**
     * 插入用户的收货地址
     * @param address 收获地址数据
     * @return 受影响的行数
     */
    Integer insert(Address address);

    /**
     * 根据用户的id统计收获地址的数量
     * @param uid 用户id
     * @return 当前用户的收获地址总数
     */
    Integer countByUid(Integer uid);

    /**
     * 根据用户的id查询用户的地址信息
     * @param uid 用户id
     * @return 用户地址信息
     */
    List<Address> findByUid(Integer uid);

    /**
     * 在设置默认地址之前查询这条地址是否存在
     * @param aid 地址id
     * @return 地址信息,如果没有找到则返回null值
     */
    Address findByAid(Integer aid);

    /**
     * 在修改用户收货地址前将所有地址设置为非默认
     * @param uid 用户id
     * @return 影响的行数
     */
    Integer updateNonDefault(Integer uid);

    /**
     * 根据地址id设置默认收货地址
     * @param aid 地址id
     * @param modifiedUser 修改者
     * @param modifiedTime 修改时间
     * @return
     */
    Integer updateDefaultByAid(@Param("aid") Integer aid, @Param("modifiedUser") String modifiedUser,
                               Date modifiedTime);

    /**
     * 根据aid删除用户收货地址
     * @param aid 收货地址id
     * @return 受影响的行数
     */
    Integer deleteByAid(Integer aid);

    /**
     * 查询用户最后修改的收货地址
     * @param uid 用户id
     * @return 收货地址,如果剩下一条地址被删除了则返回null
     */
    Address findLastModified(Integer uid);
}
