package com.guxiu.jdkaop;

/**
 * Package: com.guxiu.jdkaop
 * DESCRIPTION:
 *
 * @author guxiu2008
 * @create 2018-10-02 9:04
 **/
public class UserDaoImpl implements UserDao {
    public void addUser() {
        System.out.println("添加用户");
    }

    public void deleteUser() {
        System.out.println("删除用户");
    }
}
