package com.example.test01.interceptor;

import org.apache.ibatis.plugin.Interceptor;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;
import java.util.function.Predicate;

/**
 * @author Keifer
 * @creat 2022-02-08-16:23
 */
/*定义一个拦截器*/
public class LoginInterceptor implements HandlerInterceptor {
    /**
     * 检测全局session对象中是否有uid数据,如果有则放行，没有则重定向到登陆页面
     * @param request 请求对象
     * @param response 响应对象
     * @param handler 处理器(url+Controller:映射)
     * @return 返回值为true放行，返回值false拦截当前的请求
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {
        //通过HttpServlerRequest对象来获取session对象
        Object obj = request.getSession().getAttribute("uid");
        if(obj==null){
            //说明用户没有登录过系统，重定向到login.html页面
            response.sendRedirect("/web/login.html");
            //结束后续的调用
            return false;
        }
        //请求放行
        return true;
    }
}
