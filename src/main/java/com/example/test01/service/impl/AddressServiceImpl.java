package com.example.test01.service.impl;

import com.example.test01.entity.Address;
import com.example.test01.mapper.AddressMapper;
import com.example.test01.mapper.UserMapper;
import com.example.test01.service.IAddressService;
import com.example.test01.service.IDistrictService;
import com.example.test01.service.ex.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.beans.Transient;
import java.util.Date;
import java.util.List;

/**
 * @author Keifer
 * @creat 2022-02-18-9:43
 */
@Service
public class AddressServiceImpl implements IAddressService {
    @Autowired
    private AddressMapper addressMapper;
    //在添加用户收货地址的业务层依赖于IDistrictService的业务层接口
    @Autowired
    private IDistrictService districtService;
    @Value("${user.address.max-count}")
    private Integer maxCount;
    @Override
    public void addNewAddress(Integer uid, String username, Address address) {
        //插入之前调用收货地址统计的方法,判断是否达到上限，达到了则抛出异常 或者可以在配置文件中完成
        Integer count=addressMapper.countByUid(uid);
        if(count>maxCount){
            throw new AddressCountLimitException("收货地址达到上限");
        }
        //uid isDefault 1默认 0不默认     把前端无法传递的数据设置出来和一些逻辑的判断 如果再插入前查出来的地址数量是0，
        // 则新增的地址就自动设置为默认的，否则不设置为默认的
        Integer isDefault=count==0? 1:0;
        address.setIsDefault(isDefault);
        //补全数据将uid设置到参数address中
        address.setUid(uid);
        //补全四项日志
        address.setCreatedTime(new Date());
        address.setCreatedUser(username);
        address.setModifiedUser(username);
        address.setModifiedTime(new Date());
        //对address中的数据进行补全 省市区
        String provinceName= districtService.getNameByCode(address.getProvinceCode());
        String cityName= districtService.getNameByCode(address.getCityCode());
        String areaName=districtService.getNameByCode(address.getAreaCode());
        address.setCityName(cityName);
        address.setProvinceName(provinceName);
        address.setAreaName(areaName);
        //插入收获地址的方法
        Integer rows=addressMapper.insert(address);
        if(rows!=1){
            throw new InsertException("插入用户的收货地址产生了未知的异常");
        }
    }

    @Override
    public List<Address> getByUid(Integer uid) {
        //体量比较大，优化把不需要展示给用户的信息设置为null
        List<Address>list=addressMapper.findByUid(uid);
        for(Address d:list){
            d.setAreaCode(null);d.setCityCode(null);
            d.setProvinceCode(null);d.setCreatedUser(null);d.setCreatedTime(null);
            d.setIsDefault(null);d.setModifiedUser(null);d.setModifiedTime(null);
            d.setZip(null);
        }
        return list;
    }

    @Override
    public void setDefault(Integer uid, Integer aid, String username) {
        Address address= addressMapper.findByAid(aid);
        //查询当前收货地址是否存在
        if(address==null){
            throw new AddressNotFoundException("该用户地址不存在");
        }
        //检测当前登录用户获取的收货地址和数据库的是否一样，防止修改了其他人的
        if(!address.getUid().equals(uid)){
            throw new AccessDeniedException("非法数据访问");
        }
        Integer rows=addressMapper.updateNonDefault(uid);
        if(rows==null){
            throw new UpdateException("更新默认地址时出现未知错误");
        }
        rows=addressMapper.updateDefaultByAid(aid,username,new Date());
        if(rows==null){
            throw new UpdateException("更新默认地址时出现未知错误");

        }
    }

    @Override
    public void deleteAddress(Integer uid, Integer aid, String username) {
        //先查看当前要删除的收货地址是否存在
        Address address= addressMapper.findByAid(aid);
        if(address==null){
            throw new AddressNotFoundException("该条删除的地址不存在");
        }
        //查看当前登录的操作人的uid和后端要删除数据的uid是否一致
        if(!address.getUid().equals(uid)){
            throw new AccessDeniedException("非法删除操作");
        }
        //进行地址的删除操作
        Integer rows=addressMapper.deleteByAid(aid);
        if(rows!=1){
            throw new DeleteException("删除操作出现未知的错误");
        }
        //判断删除的地址是否是默认地址
        if(address.getIsDefault()==0){
            return;
        }
        //查询删除后剩下几条地址，若不为0条，则将最近的一条地址设置为默认地址
        Integer rows2=addressMapper.countByUid(uid);
        if(rows2!=0){
            Integer rows3=addressMapper.updateDefaultByAid(addressMapper.findLastModified(uid).getAid(),username,new Date());
            if(rows3!=1){
                throw new UpdateException("设置默认收获地址时出现未知的异常");
            }
        }else {
        return;
        }
        }

    @Override
    public Address getByAid(Integer aid, Integer uid) {
        Address address=addressMapper.findByAid(aid);
        if(address==null){
            throw new AddressNotFoundException("当前用户地址不存在");
        }
        if(!address.getUid().equals(uid)){
            throw new AccessDeniedException("非法访问");
        }
        address.setProvinceCode(null);address.setCityCode(null);address.setModifiedUser(null);
        address.setModifiedTime(null);address.setAreaCode(null);address.setCreatedTime(null);address.setModifiedUser(null);
        return address;
    }
}
