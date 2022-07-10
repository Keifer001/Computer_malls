package com.example.test01.controller;

import com.example.test01.entity.Address;
import com.example.test01.service.IAddressService;
import com.example.test01.service.ICartService;
import com.example.test01.util.JsonResult;
import com.example.test01.vo.CartVO;
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
@RequestMapping("carts")
public class CartController extends BaseController{
    @Autowired
    private ICartService cartService;
    @RequestMapping("add_to_cart")
    public JsonResult<Void> addToCart(HttpSession session,Integer pid,Integer amount){
        cartService.addToCart(amount,getuidFromSession(session),pid,getUsernameFromSession(session));
        return new JsonResult<Void>(OK);
    }
    @RequestMapping({"","/"})
    public JsonResult<List<CartVO>> getVOByUid(HttpSession session){
        List<CartVO>data=cartService.getVOByUid(getuidFromSession(session));
        return new JsonResult<List<CartVO>>(OK,data);
    }
    @RequestMapping("{cid}/num/add")
    public JsonResult<Integer>addNum(@PathVariable("cid") Integer cid,HttpSession session){
        Integer data=cartService.addNum(cid,getUsernameFromSession(session),getuidFromSession(session));
        return new JsonResult<Integer>(OK,data);
    }
    @RequestMapping("{cid}/num/delete")
    public JsonResult<Integer>deleteNum(@PathVariable("cid")Integer cid,HttpSession session){
        Integer data= cartService.deleteNum(cid,getUsernameFromSession(session),getuidFromSession(session));
        return new JsonResult<Integer>(OK,data);
    }
    @RequestMapping("list")
    public JsonResult<List<CartVO>>getVOByCids(Integer[] cids,HttpSession session){
        List<CartVO>data=cartService.getVOByCids(cids,getuidFromSession(session));
        return new JsonResult<List<CartVO>>(OK,data);
    }
}
