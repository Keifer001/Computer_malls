package com.example.test01.service.impl;

import com.example.test01.entity.Address;
import com.example.test01.entity.Cart;
import com.example.test01.entity.Product;
import com.example.test01.mapper.AddressMapper;
import com.example.test01.mapper.CartMapper;
import com.example.test01.mapper.ProductMapper;
import com.example.test01.service.IAddressService;
import com.example.test01.service.ICartService;
import com.example.test01.service.IDistrictService;
import com.example.test01.service.IProductService;
import com.example.test01.service.ex.*;
import com.example.test01.vo.CartVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * @author Keifer
 * @creat 2022-02-18-9:43
 */
@Service
public class CartServiceImpl implements ICartService {
    //购物车的业务层依赖于购物车的持久层和商品的持久层
    @Autowired
    private CartMapper cartMapper;
    @Autowired
    private ProductMapper productMapper;
    @Override
    public void addToCart(Integer amount, Integer uid, Integer pid, String username) {
        Cart cart=cartMapper.findByUidAndPid(uid,pid);
        if(cart!=null){
            //如果已经存在数据则新增的数量加上原来数据库中的数量等于总的数量
            Integer num=amount+cart.getNum();
            Integer rows=cartMapper.updateNumByCid(cart.getCid(),num,username,new Date());
            if(rows!=1){
                throw new UpdateException("更新购物车数量时出现未知错误");
            }
        }else{
            Cart cart1=new Cart();
            Product product=productMapper.findById(pid);
            // 调用productService.findById(pid)查询商品数据，得到商品价格
            cart1.setPrice(product.getPrice());
            cart1.setUid(uid);
            cart1.setPid(pid);
            //之前没有新增，则总的数量就等于现在新增的数量
            cart1.setNum(amount);
            cart1.setCreatedUser(username);
            cart1.setCreatedTime(new Date());
            cart1.setModifiedTime(new Date());
            cart1.setModifiedUser(username);
            Integer rows2=cartMapper.insert(cart1);
            if(rows2!=1){
                throw new InsertException("购物车插入时出现未知的异常");
            }
        }
    }

    @Override
    public List<CartVO> getVOByUid(Integer uid) {
        List<CartVO>cartVOS =cartMapper.findVOByUid(uid);
        return cartVOS;
    }

    @Override
    public Integer addNum(Integer cid, String username, Integer uid) {
        Cart cart= cartMapper.findByCid(cid);
        //商品是否存在和登录的账户是否与要更新的购物车数量的账户一致
        if(cart==null){
            throw new CartNotFoundException("购物车中该条商品不存在");
        }else if(!cart.getUid().equals(uid)){
            throw new AccessDeniedException("非法访问");
        }
        //购物车商品数量加一
        Integer num=cart.getNum()+1;
        Integer rows=cartMapper.updateNumByCid(cid,num,username,new Date());
        if(rows!=1){
            throw new UpdateException("插入是出现未知的异常");
        }
        return num;
    }

    @Override
    public Integer deleteNum(Integer cid, String username, Integer uid) {
        Cart cart=cartMapper.findByCid(cid);
        if(cart==null){
            throw new CartNotFoundException("当前购物车商品不存在");
        }else if(!cart.getUid().equals(uid)){
            throw new AccessDeniedException("非法访问");
        }
        Integer num=cart.getNum()-1;
        Integer rows=cartMapper.updateNumByCid(cid,num,username,new Date());
        if(rows==null){
            throw new UpdateException("更新出现未知的异常");
        }
        return num;
    }

    @Override
    public List<CartVO> getVOByCids(Integer[] cids, Integer uid) {
        List<CartVO> list=cartMapper.findVOByCids(cids);
        Iterator<CartVO> it=list.iterator();
        while (it.hasNext()){
            CartVO cartVO= it.next();
            if(!uid.equals(cartVO.getUid())){
                it.remove();
            }
            if(it==null){
                throw new CartNotFoundException("该商品数据不存在");
            }
        }
        return list;
    }

}
