package com.guxiu.aspectjannotation;

import org.springframework.stereotype.Component;

/**
 * Package: com.guxiu.jdkaop
 * DESCRIPTION:
 *
 * @author guxiu2008
 * @create 2018-10-02 9:04
 **/
@Component
public class UserDao {
    public void addUser() {
        System.out.println("添加用户");
    }

    public void deleteUser() {
        System.out.println("删除用户");
    }
}
