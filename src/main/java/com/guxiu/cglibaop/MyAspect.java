package com.guxiu.cglibaop;

/**
 * Package: com.guxiu.jdkaop
 * DESCRIPTION:
 *
 * @author guxiu2008
 * @create 2018-10-02 9:05
 **/
public class MyAspect {
    public void check_Permissions() {
        System.out.println("模拟检查权限...");
    }

    public void log() {
        System.out.println("模拟记录日志...");
    }
}
