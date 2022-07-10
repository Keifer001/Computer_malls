package com.example.test01.controller;

import com.example.test01.entity.Address;
import com.example.test01.service.IAddressService;
import com.example.test01.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author Keifer
 * @creat 2022-02-18-11:17
 */
@RestController
@RequestMapping("addresses")
public class AddressController extends BaseController{
    @Autowired
    private IAddressService addressService;
    @RequestMapping("add_new_address")
    public JsonResult<Void> addNewAddress(HttpSession session, Address address){
        String username=getUsernameFromSession(session);
        Integer uid=getuidFromSession(session);
        addressService.addNewAddress(uid,username,address);
        return new JsonResult<Void>(OK);
    }
    @RequestMapping({"","/"})
    public JsonResult<List<Address>> getByUid(HttpSession session){
        Integer uid=getuidFromSession(session);
        List<Address> data=addressService.getByUid(uid);
        return new JsonResult<>(OK,data);
    }
    //{}后端需要的参数将在前端以访问路径的RestFul风格的url进行解析传递
    @RequestMapping("{aid}/set_default")
    public JsonResult<Void>setDefaultAddress(HttpSession session,@PathVariable("aid") Integer aid){
        addressService.setDefault(getuidFromSession(session),aid,getUsernameFromSession(session));
        return new JsonResult<>(OK);
    }
    //{}后端需要的根据aid删除地址的参数将在前端以访问路径的RestFul风格的url进行解析传递  aid要加注解将前端RestFul风格传递的aid和后端的aid绑定
    @RequestMapping("{aid}/delete_address")
    public JsonResult<Void>deleteAddress(HttpSession session,@PathVariable("aid")Integer aid){
        addressService.deleteAddress(getuidFromSession(session), aid,getUsernameFromSession(session));
        return new JsonResult<Void>(OK);
    }
}
