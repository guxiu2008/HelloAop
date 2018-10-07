package com.guxiu.aspectjannotation;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Package: com.guxiu.aspectjannotation
 * DESCRIPTION:
 *
 * @author guxiu2008
 * @create 2018-10-07 20:51
 **/
public class AspectAnnotationTestApp {
    public static void main(String[] args) {
        String xmlPath = "aspectjannotation/applicationContext.xml";
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext(xmlPath);
        //从spring容器获得内容
        UserDao userDao = (UserDao) applicationContext.getBean("userDao");
        //执行方法
        userDao.addUser();
        userDao.deleteUser();
    }
}
