package com.lutfly.guava.demo;

import com.google.common.util.concurrent.RateLimiter;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

import static java.util.concurrent.TimeUnit.MICROSECONDS;

/**
 * guava 限流 test
 *
 * @author lutong
 * @since 2020/11/25
 */
public class RateLimiterTest {

    static RateLimiter limiter = RateLimiter.create(2);

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            boolean b = limiter.tryAcquire(2,0, MICROSECONDS);
//            double b = limiter.acquire();
//            if (b) {
                System.out.println(i + "   " + b);
//            }
            LockSupport.parkNanos(TimeUnit.MILLISECONDS.toNanos(800));
        }
    }




}
