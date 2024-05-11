package com.zack.bookserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cglib.proxy.Enhancer;

import java.lang.reflect.Proxy;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

@SpringBootApplication
public class BookserverApplication {

    public static void main(String[] args) {
//        new ReentrantReadWriteLock();
//        Proxy.newProxyInstance();
//        Enhancer.create();
//        LockSupport.park();
//        new CyclicBarrier(1).await();

        SpringApplication.run(BookserverApplication.class, args);
    }

}
