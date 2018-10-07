package com.guxiu.jdkaop;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Package: com.guxiu.jdkaop
 * DESCRIPTION:
 *
 * @author guxiu2008
 * @create 2018-10-02 9:07
 **/
public class JdkProxy implements InvocationHandler {

    private UserDao userDao;

    public Object createProxy(UserDao userDao) {
        this.userDao = userDao;
        //1.类加载器
        ClassLoader classLoader = JdkProxy.class.getClassLoader();
        //2.加载被代理对象实现的所有接口
        Class[] clazz = userDao.getClass().getInterfaces();
        //3.使用代理类,进行增强,返回的是代理后的对象
        return Proxy.newProxyInstance(classLoader, clazz, this);

    }
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //声明切面
        MyAspect myAspect = new MyAspect();
        //前增强
        myAspect.check_Permissions();
        //在目标类上调用方法,并传入参数,返回的Object对象相当于目标类调用方法的返回值
        Object obj = method.invoke(userDao, args);
        //后增强
        myAspect.log();
        //返回目标类方法的返回值
        return obj;
    }
}
