package com.example.test01.controller;

import com.example.test01.entity.Address;
import com.example.test01.entity.Product;
import com.example.test01.service.IAddressService;
import com.example.test01.service.IProductService;
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
@RequestMapping("products")
public class ProductController extends BaseController{
    @Autowired
    private IProductService productService;
    @RequestMapping("hot_list")
    public JsonResult<List<Product>> getHotList(){
        List<Product>data=productService.findHotList();
        return new JsonResult<List<Product>>(OK,data);
    }
    @RequestMapping("{id}/details")
    public JsonResult<Product>getById(@PathVariable("id") Integer id){
        Product data=productService.findById(id);
        return new JsonResult<Product>(OK,data);
    }
}
