package com.androidpro.bean;

/**
 * @author chenzhiwei
 * @version 1.0
 * @date 2019/6/27 下午4:18
 * @description
 */
public class TestSynchronizedK {
    private String mTest;

    private synchronized void open(){
        //普通方法锁，锁的是当前实例对象，访问需要获取当前对象锁
    }

    public static synchronized void close(){
        //静态方法锁，锁的是类对象，每个class类都有一个类对象
    }

    private void openBlock(){
        synchronized (mTest){
            //锁的是当前括号内的对象，
        }
        synchronized (mTest) {
            //如果是一个类对象，也就是所谓的类锁了
        }
    }
}
