package com.example.test01.configure;

import com.example.test01.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Keifer
 * @creat 2022-02-08-17:42
 */
/*处理器拦截器的注册*/
    @Configuration //加载当前的拦截器并进行注册
public class LoginInterceptorConfigure implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //创建自定义的拦截器对象
        HandlerInterceptor interceptor=new LoginInterceptor();
        //配置白名单，因为源代码要求存放在list集合中，所以存放在list集合里
        List<String> patterns=new ArrayList<>();
        patterns.add("/bootstrap3/**");                              //白名单的内容主要看static里的
        patterns.add("/css/**");               //1.所有的样式设计 2.登录、注册、首页、产品 页面 3.两个url路径
        patterns.add("/images/**");
        patterns.add("/js/**");
        patterns.add("/web/login.html");
        patterns.add("/web/register.html");
        patterns.add("/web/index.html");
        patterns.add("/web/product.html");
        patterns.add("/users/login");
        patterns.add("/users/reg");
        patterns.add("/districts/**");
        patterns.add("/products/**");
        //完成拦截器的注册
        registry.addInterceptor(interceptor)
                .addPathPatterns("/**")               //添加到拦截器即黑名单中
                .excludePathPatterns(patterns);         //除了**不添加到拦截器中
    }
    //拦截器实现WebMvcConfigurer接口 重写注册拦截器的方法 方法中就有以参数形式传递的对象 其中有其接口所需要的方法
}
