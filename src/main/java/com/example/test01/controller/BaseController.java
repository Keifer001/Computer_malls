package com.example.test01.controller;

import com.example.test01.controller.ex.*;
import com.example.test01.service.ex.*;
import com.example.test01.util.JsonResult;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpSession;

/**
 * @author Keifer
 * @creat 2022-02-02-13:00
 */
/*在控制层基类当中去统一处理异常-，并且在工具类中创建异常状态码、信息、泛型数据，在控制层基类中实例化出来，给每一个异常一个唯一的编码*/
public class BaseController {
    public static final int OK=200;
    /**
     * 被@ExceptionHandle修饰的方法会自动将异常对象传递给此方法的参数列表上
     *当项目中产生了异常，会被统一拦截到此方法中，这个方法此时充当的就是请求处理方法，方法的返回值直接给到前端
     * @param e 异常对象
     * @return 返回的json格式数据
     */                                  //统一处理的异常必须是class类型，增加新的异常处理用逗号分隔
    @ExceptionHandler({ServiceException.class,FileUploadException.class})
    public JsonResult<Void> handleException(Throwable e){
        JsonResult<Void> result=new JsonResult<Void>(e);
        if(e instanceof UsernameDuplicateException){
            result.setState(4000);
            result.setMessage("该用户名被占用");
        }else if(e instanceof InsertException){
            result.setMessage("插入时产生了未知的异常");
            result.setState(5000);
        }else if(e instanceof UserNotFoundException){
            result.setState(4001);
            result.setMessage("该用户数据不存在");
        } else if(e instanceof PasswordNotMatchException){
            result.setState(4002);
            result.setMessage("密码错误");
        }else if(e instanceof UpdateException){
            result.setState(5001);
            result.setMessage("更新数据时产生了未知的异常");
        }else if(e instanceof DeleteException){
            result.setState(5002);
            result.setMessage("删除用户收货地址时产生了未知的异常");
        }
        else if(e instanceof AddressCountLimitException){
            result.setState(4003);
            result.setMessage("用户的收货地址超出上限");
        }else if(e instanceof AddressNotFoundException){
            result.setState(4004);
            result.setMessage("用户地址不存在");
        }else if(e instanceof AccessDeniedException){
            result.setState(4005);
            result.setMessage("非法数据访问");
        }else if(e instanceof ProductNotFoundException){
            result.setState(4006);
            result.setMessage("该商品不存在");
        }
        else if(e instanceof FileEmptyException){
            result.setState(6000);
        }else if(e instanceof FileSizeException){
            result.setState(6001);
        }else if(e instanceof FileTypeException){
            result.setState(6002);
        }else if(e instanceof FileStateException){
            result.setState(6003);
        }else if(e instanceof FileUploadException){
            result.setState(6004);
        }else if(e instanceof CartNotFoundException){
            result.setState(4007);
            result.setMessage("该商品在购物车中不存在");
        }
        return result;
    }

    /**
     * 获取session对象中的uid
     * @param session session对象
     * @return 当前登录用户uid的值
     */
    protected final Integer getuidFromSession(HttpSession session){
        return Integer.valueOf(session.getAttribute("uid")
                .toString()); //因为valueOf(String str)返回保存指定的String值的Integer对象，所以该对象要先转换为String
        //toString返回一个表示该Integer值的String对象
    }

    /**
     * 获取当前登录用户的username
     * @param session session对象
     * @return 当前登录用户的用户名称
     */
    protected final String getUsernameFromSession(HttpSession session){
        return session.getAttribute("username").toString();
    }
}
