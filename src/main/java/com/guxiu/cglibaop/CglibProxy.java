package com.guxiu.cglibaop;


import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * Package: com.guxiu.cglibaop
 * DESCRIPTION:
 *
 * @author guxiu2008
 * @create 2018-10-04 8:28
 **/
public class CglibProxy implements MethodInterceptor {
    //代理方法
    public Object createProxy(Object target) {
        //创建一个动态类对象
        Enhancer enhancer = new Enhancer();
        //确定需要增强的类，设置其父类
        enhancer.setSuperclass(target.getClass());
        //添加毁掉函数
        enhancer.setCallback(this);
        //返回创建的代理类
        return enhancer.create();
    }

    /**
     * proxy CGlib根据指定父类生成的代理对象
     * @param o
     * @param method 拦截的方法
     * @param objects 拦截方法的参数数组
     * @param methodProxy 方法的代理对象，用于执行父类的方法
     * @return
     * @throws Throwable
     */
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        //创建切面类对象
        MyAspect myAspect = new MyAspect();
        //前增强
        myAspect.check_Permissions();
        //目标方法执行
        Object obj = methodProxy.invokeSuper(o, objects);
        //后增强
        myAspect.log();
        return obj;
    }
}
