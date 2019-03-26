package com.zpj.threadLocalTest;

import com.google.common.annotations.VisibleForTesting;

public class ThreadLocalDemo {

    ThreadLocal threadLocal = new ThreadLocal();
    ThreadLocal<Long> longLocal = new ThreadLocal<Long>();
    ThreadLocal<String> stringLocal = new ThreadLocal<String>();

    public ThreadLocal getThreadLocal() {
        return threadLocal;
    }
    public long getLong() {
        return longLocal.get();
    }

    public String getString() {
        return stringLocal.get();
    }
    public void setThreadLocal() {

        threadLocal.set(Thread.currentThread().getId()+"===========");
        threadLocal.set(Thread.currentThread().getName()+"=============");
        longLocal.set(Thread.currentThread().getId());
        stringLocal.set(Thread.currentThread().getName()+"=============");
       // this.threadLocal = threadLocal;
    }

    public static  void  main(String[] args){

        final ThreadLocalDemo threadLocalDemo = new ThreadLocalDemo();
        threadLocalDemo.setThreadLocal();
        System.out.println("1.0   "+threadLocalDemo.getThreadLocal());
        System.out.println("1.1     "+threadLocalDemo.getLong());
        System.out.println("1.2    "+threadLocalDemo.getString());

        Thread thread = new Thread(){
            public void run(){
                threadLocalDemo.setThreadLocal();
                System.out.println("2.0   "+threadLocalDemo.getThreadLocal());
                System.out.println("2.1   "+threadLocalDemo.getLong());
                System.out.println("2.2   "+threadLocalDemo.getString());
            }
        };
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("3.0   "+threadLocalDemo.getThreadLocal());
        System.out.println("3.1   "+threadLocalDemo.getLong());
        System.out.println("3.2   "+threadLocalDemo.getString());
    }
}
