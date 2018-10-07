package com.guxiu.cglibaop;

/**
 * Package: com.guxiu.cglibaop
 * DESCRIPTION:
 *
 * @author guxiu2008
 * @create 2018-10-04 8:38
 **/
public class CgLibTestApp {
    public static void main(String[] args) {
        //创建代理对象
        CglibProxy cglibProxy = new CglibProxy();
        //创建目标对象
        UserDao userDao = new UserDao();
        //获取增强后的目标对象
        UserDao u = (UserDao) cglibProxy.createProxy(userDao);
        //执行方法
        u.addUser();
        u.deleteUser();
    }
}
