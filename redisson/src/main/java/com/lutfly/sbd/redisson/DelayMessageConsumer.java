package com.lutfly.sbd.redisson;

import org.redisson.Redisson;
import org.redisson.api.RBlockingQueue;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * description: TODO
 * Author	Date	Changes
 * lutong  2020/5/6 Created
 */
public class DelayMessageConsumer {

    public static void main(String[] args) {

        for (int i = 0; i < 3; i++) {
            new Thread(() -> {
                RedissonClient redissonClient = getRedissonClient();
                RBlockingQueue<String> blockingFairQueue = redissonClient.getBlockingQueue("delay_demo");
                while (true) {
                    String callCdr = null;
                    try {
                        callCdr = blockingFairQueue.take();
                    } catch (InterruptedException e) { }
                    System.out.println("订单取消时间：" + LocalTime.now().format(DateTimeFormatter.ofPattern("mm:ss")) + "==订单生成时间" + callCdr.substring(8)+"   "+Thread.currentThread().getName());
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

}
