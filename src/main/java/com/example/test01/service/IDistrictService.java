package com.example.test01.service;

import com.example.test01.entity.District;

import java.util.List;

/**
 * @author Keifer
 * @creat 2022-02-19-18:05
 */
public interface IDistrictService {
    /**
     * 根据父代号来查询区域信息(省市区)
     * @param parent 父代号
     * @return 区域信息
     */
    List<District> getDistrictByparent(String parent);
    String getNameByCode(String code);
}
