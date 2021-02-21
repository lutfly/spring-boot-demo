package com.lutfly.sbd.redisson;

import com.sun.tools.javac.util.Assert;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.redisson.Redisson;
import org.redisson.api.RBlockingQueue;
import org.redisson.api.RDelayedQueue;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

/**
 * description: TODO
 * Author	Date	Changes
 * lutong  2020/5/6 Created
 */
public class DelayedMessage {

    public static void main(String[] args) {
        RedissonClient redissonClient = getRedissonClient();
        RBlockingQueue<Employer> blockingFairQueue = redissonClient.getBlockingQueue("delay_demo");
        RDelayedQueue<Employer> delayedQueue = redissonClient.getDelayedQueue(blockingFairQueue);

        for (int i = 0; i < 10; i++) {
            Employer callCdr = new Employer("ZhangSan"+i,LocalTime.now().format(DateTimeFormatter.ofPattern("mm:ss")));
            delayedQueue.offer(callCdr, 10+i, TimeUnit.SECONDS);
            System.out.println("---订单创建：" + callCdr.getName() + "  订单生成时间" + callCdr.getPutTime());
        }
        new Thread(() -> {
            LockSupport.parkNanos(15*1000000000);
            RedissonClient redissonClient2 = getRedissonClient();
            RBlockingQueue<Employer> blockingFairQueue2 = redissonClient2.getBlockingQueue("delay_demo");
//            blockingFairQueue2
        }).start();

        for (int i = 0; i < 3; i++) {
            new Thread(() -> {
                RedissonClient redissonClient1 = getRedissonClient();
                RBlockingQueue<Employer> blockingFairQueue1 = redissonClient1.getBlockingQueue("delay_demo");
                while (true) {
                    Employer callCdr = null;
                    try {
                        callCdr = blockingFairQueue1.take();
                    } catch (InterruptedException e) { }
                    System.out.println("订单取消时间：" + LocalTime.now().format(DateTimeFormatter.ofPattern("mm:ss")) + "==订单生成时间" + callCdr.getPutTime()+"   "+Thread.currentThread().getName());
                }
            }).start();
        }
    }

    private static RedissonClient getRedissonClient() {
        Config config = new Config();
        config.useSingleServer()
                .setAddress("redis://127.0.0.1:6379").setDatabase(1);
        return Redisson.create(config);
    }


    @Data
    @ToString
    static
    class Employer implements Serializable {
        private String name;
        private String putTime;

        public Employer(String name, String putTime) {
            this.name = name;
            this.putTime = putTime;
        }

        public void setPutTime() {
            this.putTime = LocalTime.now().format(DateTimeFormatter.ofPattern("mm:ss"));
        }
    }
}
