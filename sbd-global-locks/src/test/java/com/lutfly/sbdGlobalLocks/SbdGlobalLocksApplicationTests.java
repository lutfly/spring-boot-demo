package com.lutfly.sbdGlobalLocks;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.integration.redis.util.RedisLockRegistry;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class SbdGlobalLocksApplicationTests {

    @Test
    public void contextLoads() {
    }

    @Autowired
    RedisLockRegistry redisLockRegistry;

    private Integer i =0;


    @Test
    public void test() throws InterruptedException {
        for (int j = 0; j < 10; j++) {
            new Thread(() -> {
                Lock lock = redisLockRegistry.obtain("lock");
                try {
                    while (true) {
                        lock.lock();
                        if (lock.tryLock(3, TimeUnit.SECONDS)) {
                            try {
                                System.out.println(Thread.currentThread().getName()+"  获取锁成功   " + i++);
                                TimeUnit.MILLISECONDS.sleep(300);
                            } finally {
                                lock.unlock();
                                TimeUnit.MILLISECONDS.sleep(10);
                            }
                        } else {
                            System.out.println(Thread.currentThread().getName()+"  获取锁失败   ");
                        }
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }).start();
        }
        TimeUnit.SECONDS.sleep(30000);
    }


    @Test
    public void test1() throws InterruptedException {
        Lock lock = redisLockRegistry.obtain("lock");
        while (true) {
            if (lock.tryLock(1, TimeUnit.SECONDS)) {
                try {
                    System.out.println("  获取锁成功   "+ i++);
                    TimeUnit.SECONDS.sleep(3);
                } finally {
                    lock.unlock();
                    TimeUnit.MILLISECONDS.sleep(10);
                }
            } else {
                System.out.println("  获取锁失败   ");
            }
        }
    }

    @Test
    public void ReentrantLockTest() throws InterruptedException {
        for (int j = 0; j < 5; j++) {
            new Thread(this::temp).start();
        }
        TimeUnit.SECONDS.sleep(30000);
    }


    private  void  temp(){
        int userId = 1064699649;
        Lock lock = redisLockRegistry.obtain("pointChange_" + userId);
        try {
            if (lock.tryLock(5, TimeUnit.SECONDS)) {
                try {
                    log.error(Thread.currentThread().getName()+"  ############## " + ++i);
                    TimeUnit.SECONDS.sleep(1);
                } finally {
                    lock.unlock();
                    log.error(Thread.currentThread().getName()+" 解锁");

                }
            } else {
                //TODO 获取锁失败补偿
                log.error("获取锁失败!  userId : {} ", userId);
            }
        } catch (InterruptedException e) {
            log.error("终端异常.", e);
        }
    }


    //@Test
    public void testMap() {
        Map<String, String> map = new HashMap<>();
        map.put("a", "A");
        map.put("b", "B");
//        String a = map.compute("b",(k,v)-> "v");  // 输出 B
        String v2 = map.computeIfAbsent("b", a -> "v");  // 输出 B
        System.out.println(v2);
        String v1 = map.putIfAbsent("c", "v");  // 输出 null
        System.out.println(v1);
    }
}
