package com.example.test01.mapper;

import com.example.test01.entity.Address;
import com.example.test01.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

/**
 * @author Keifer
 * @creat 2022-01-27-10:02
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class AddressMapperTests {
    @Autowired
    private AddressMapper addressMapper;
    @Test
    public void insert(){
        Address address=new Address();
        address.setUid(18);
        address.setName("admin");
        address.setPhone("15260826020");
        address.setAddress("雁塔区小寨赛格");
        address.setCityName("杭州市");
        address.setProvinceName("浙江省");
        address.setAreaName("西湖区");
        Integer rows=addressMapper.insert(address);
        System.out.println(rows);
    }
    @Test
    public void countByUid(){
        Integer num= addressMapper.countByUid(18);
        System.out.println(num);
    }
    @Test
    public void findByUid(){
        List<Address> result= addressMapper.findByUid(25);
        System.out.println(result);
    }
    @Test
    public void findByAid(){
        Address address= addressMapper.findByAid(26);
        System.out.println(address);
    }
    @Test
    public void updateNonDefault(){
        Integer rows=addressMapper.updateNonDefault(34);
        System.out.println(rows);
    }
    @Test
    public void updateDefaultByAid(){
        Integer rows=addressMapper.updateDefaultByAid(26,"管理员",new Date());
        System.out.println(rows);
    }
    @Test
    public void deleteByAid(){
        Integer rows=addressMapper.deleteByAid(30);
        System.out.println(rows);
    };
    @Test
    public void findLastModified(){
        Address address=addressMapper.findLastModified(34);
        System.out.println(address);
    };
}

