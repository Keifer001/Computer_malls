package com.example.test01.service;

import com.example.test01.entity.Address;
import com.example.test01.entity.User;
import com.example.test01.service.ex.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

/**
 * @author Keifer
 * @creat 2022-01-27-11:14
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class AddressServiceTests {
    @Autowired
    private IAddressService addressService;
    @Test
    public void addNewAddress(){
        Address address=new Address();
        address.setUid(25);
        address.setName("admin");
        address.setPhone("1526666");
        address.setAddress("A区B街道");
        address.setProvinceCode("110101");
        address.setCityCode("110101");
        address.setAreaCode("110101");
        addressService.addNewAddress(34,"管理员",address);
    }
    @Test
    public void setDefault(){
        addressService.setDefault(34,27,"管理员");
    }
    @Test
    public void deleteAddress() {
        try {
            addressService.deleteAddress(34,27,"管理员");
            System.out.println("OK");
        } catch (ServiceException e) {
            System.out.println(e.getClass().getSimpleName());
            System.out.println(e.getMessage());
        }
    }
    @Test
    public void getByAid(){
        Address address= addressService.getByAid(1,18);
        System.out.println(address);
    }
}
