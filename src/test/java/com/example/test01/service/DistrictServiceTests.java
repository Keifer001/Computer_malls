package com.example.test01.service;

import com.example.test01.entity.Address;
import com.example.test01.entity.District;
import com.example.test01.service.impl.DistrictServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @author Keifer
 * @creat 2022-01-27-11:14
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class DistrictServiceTests {
    @Autowired
    private DistrictServiceImpl districtService;
    @Test
    public void getDistrictByparent(){
        List<District> list=districtService.getDistrictByparent("110100");
        for(District d:list){
            System.out.println(d);
        }
    }
}
