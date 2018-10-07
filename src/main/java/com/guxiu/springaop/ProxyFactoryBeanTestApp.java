package com.guxiu.springaop;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Package: com.guxiu.springaop
 * DESCRIPTION:
 *
 * @author guxiu2008
 * @create 2018-10-05 9:01
 **/
public class ProxyFactoryBeanTestApp {
    public static void main(String[] args) {
        String xmlPath = "springaop/applicationContext.xml";
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext(xmlPath);
        //从spring容器获得内容
        UserDao userDao = (UserDao) applicationContext.getBean("userDaoProxy");
        //执行方法
        userDao.addUser();
        userDao.deleteUser();
    }
}
