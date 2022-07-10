package com.example.test01.controller;

import com.example.test01.entity.Address;
import com.example.test01.entity.District;
import com.example.test01.service.IAddressService;
import com.example.test01.service.IDistrictService;
import com.example.test01.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author Keifer
 * @creat 2022-02-18-11:17
 */
@RestController
@RequestMapping("districts")
public class DistrictController extends BaseController{
    @Autowired
    private IDistrictService districtService;
    //districts开头的请求有无加斜杆都被拦截到getDsitrictByParent方法里 此控制层用于关联省市区的选择表
    @RequestMapping({"/",""})
    public JsonResult<List<District>> getByparent(String parent){
        List<District> data=districtService.getDistrictByparent(parent);
        return new JsonResult<>(OK,data);
    }
}
