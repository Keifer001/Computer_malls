package com.example.test01.service.impl;

import com.example.test01.entity.District;
import com.example.test01.mapper.DistrictMapper;
import com.example.test01.service.IDistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Keifer
 * @creat 2022-02-19-18:25
 */
@Service
public class DistrictServiceImpl implements IDistrictService {
    @Autowired
    private DistrictMapper districtMapper;
    @Override
    public List<District> getDistrictByparent(String parent) {
        List<District> list=districtMapper.findByParent(parent);
        /*在进行网络数据传输时，为了尽量避免无效数据的传递，可以将无效数据设置为null，一方面节省流量，另一方面提高效率*/
        for (District d:list){
            d.setParent(null);
            d.setId(null);
        }
        return list;
    }

    @Override
    public String getNameByCode(String code) {
        return districtMapper.findNameByCode(code);
    }
}
