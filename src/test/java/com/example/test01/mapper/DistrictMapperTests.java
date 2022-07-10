package com.example.test01.mapper;

import com.example.test01.entity.Address;
import com.example.test01.entity.District;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @author Keifer
 * @creat 2022-01-27-10:02
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class DistrictMapperTests {
    @Autowired
    private DistrictMapper districtMapper;

    @Test
    public void findByParent(){
        List<District> list=districtMapper.findByParent("110100");
        for(District d : list){
            System.out.println(d);
        }
    }
    @Test
    public void findNameByCode(){
        String result=districtMapper.findNameByCode("110101");
        System.out.println(result);
    }

}

