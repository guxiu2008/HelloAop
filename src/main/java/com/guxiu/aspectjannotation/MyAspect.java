package com.guxiu.aspectjannotation;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * Package: com.guxiu.springaop
 * DESCRIPTION:
 * 切面类, 在此类仲编写通知
 * @author guxiu2008
 * @create 2018-10-05 8:49
 **/
@Aspect
@Component
public class MyAspect {
    //定义切入点表达式
    @Pointcut("execution(* com.guxiu.aspectjannotation.*.*(..))")
    //使用一个返回值为void、方法体为空的方法来命名切入点
    private void myPolintCut() {}

    //前置通知
    @Before("myPolintCut()")
    public void myBefore(JoinPoint joinPoint) {
        System.out.print("前置通知 : 模拟执行权限检查...,");
        System.out.print("目标类是: " + joinPoint.getTarget());
        System.out.println(",被植入增强处理的方法为: " + joinPoint.getSignature().getName());
    }

    //后置通知
    @AfterReturning(value = "myPolintCut()", returning = "returnVal")
    public void myAfterReturning(JoinPoint joinPoint, Object returnVal) {
        System.out.print("后置通知 : 模拟记录日志...,");
        System.out.println("被植入增强处理的方法为: " + joinPoint.getSignature().getName());
    }

    /**
     * 环绕通知
     * ProceedingJoinPoint是JoinPoint子接口,表示可以执行目标方法
     * 1.必须是Object类型的返回值
     * 2.必须接受一个参数,类型为ProceedingJoinPoint
     * 3.必须throws Throwable
     */
    @Around("myPolintCut()")
    public Object myAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        //开始
        System.out.println("环绕开始 : 执行目标方法之前, 模拟开启事务...");
        //执行当前目标方法
        Object obj = proceedingJoinPoint.proceed();
        //结束
        System.out.println("环绕开始 : 执行目标方法之前, 模拟关闭事务...");
        return obj;
    }

    //异常通知
    @AfterThrowing(value = "myPolintCut()", throwing = "e")
    public void myAfterThrowing(JoinPoint joinPoint, Throwable e) {
        System.out.println("异常通知 : 出错了" + e.getMessage());
    }

    //最终通知
    @After("myPolintCut()")
    public void myAfter() {
        System.out.println("最终通知 : 模拟方法结束后的释放资源...");
    }
}
