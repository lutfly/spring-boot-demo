package com.lutfly.guava.demo;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Caffeine Cache系统 demo
 *
 * @author lutong
 * @since 2020/7/9
 */
public class CacheTests {

    public static final Cache<Integer, Object> cache;

    static {
        cache = Caffeine.newBuilder()
//                .expireAfterWrite(2, TimeUnit.SECONDS)
//                .expireAfterAccess(2, TimeUnit.SECONDS)
                .maximumSize(10)
                .build();
//                .build(key -> getKey());
    }

    public static void main(String[] args) {
/*
        get方法会更新cache
 */
        new Timer("cache_timer", false).schedule(new TimerTask() {
            final AtomicInteger i = new AtomicInteger();

            @Override
            public void run() {
                Object ifPresent1 = cache.getIfPresent(1);
                System.out.println(Thread.currentThread().getName() + " ???  " + ifPresent1);
                Object o = cache.get(1, key -> getKey());
                System.out.println(Thread.currentThread().getName() + "      " + o);
                Object ifPresent = cache.getIfPresent(1);
                System.out.println(Thread.currentThread().getName() + " ###  " + ifPresent);
//                cache.invalidate(1);
            }

            private int getKey() {
                return i.incrementAndGet();
            }


        }, 0, 1000L);

        System.out.println("aaaaaaa");
    }

}
