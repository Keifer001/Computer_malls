package com.example.test01.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * @author Keifer
 * @creat 2022-03-10-17:32
 */
//统计业务方法执行时长
@Component //将当前对象的创建使用维护交由Spring容器来维护
@Aspect //将当前类标记为切面类
public class TimeAspect {
    //第一个星返回值不关注 第二个星表示哪一个类 第三个星表示方法 第四个..表示参数列表不关注
    @Around("execution(* com.example.test01.service.impl.*.*(..))")
                        //表示连接点,目标方法的对象
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        //先记录当前时间
        long start=System.currentTimeMillis();
        // 执行连接点方法，即切面所在位置对应的方法。本项目中表示执行注册或执行登录等
        Object result= pjp.proceed();  //调用目标方法:login
        //后记录当前时间
        long end=System.currentTimeMillis();
        System.out.println("耗时:"+(end-start));
        return result;
    }
}
