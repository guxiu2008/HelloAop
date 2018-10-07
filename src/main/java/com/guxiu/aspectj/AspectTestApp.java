package com.guxiu.aspectj;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Package: com.guxiu.aspectj
 * DESCRIPTION:
 *
 * @author guxiu2008
 * @create 2018-10-07 12:00
 **/
public class AspectTestApp {
    public static void main(String[] args) {
        String xmlPath = "aspectj/applicationContext.xml";
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext(xmlPath);
        //从spring容器获得内容
        UserDao userDao = (UserDao) applicationContext.getBean("userDao");
        //执行方法
        userDao.addUser();
        userDao.deleteUser();
    }
}
