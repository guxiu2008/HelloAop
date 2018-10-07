package com.guxiu.springaop;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

/**
 * Package: com.guxiu.springaop
 * DESCRIPTION:
 *
 * @author guxiu2008
 * @create 2018-10-05 8:49
 **/
public class MyAspect implements MethodInterceptor {
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        check_Permissions();
        //执行目标方法
        Object obj = methodInvocation.proceed();
        log();
        return obj;
    }

    public void check_Permissions() {
        System.out.println("模拟检查权限...");
    }

    public void log() {
        System.out.println("模拟记录日志...");
    }
}
