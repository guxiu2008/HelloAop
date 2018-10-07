package com.guxiu.jdkaop;

/**
 * Package: com.guxiu.jdkaop
 * DESCRIPTION:
 *
 * @author guxiu2008
 * @create 2018-10-02 9:17
 **/
public class JdkTestApp {
    public static void main(String[] args) {
        //创建代理对象
        JdkProxy jdkProxy = new JdkProxy();
        //创建目标对象
        UserDao userDao = new UserDaoImpl();
        //从代理兑对象中获取增强后的目标对象
        UserDao userDaoProxy = (UserDao) jdkProxy.createProxy(userDao);
        //执行方法
        userDaoProxy.addUser();
        userDaoProxy.deleteUser();
    }
}
