package com.lutfly.sbd.redisson;

import org.redisson.Redisson;
import org.redisson.api.RBlockingQueue;
import org.redisson.api.RDelayedQueue;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

/**
 * description: TODO
 * Author	Date	Changes
 * lutong  2020/5/6 Created
 */
public class DelayMessageProvider {
    public static void main(String[] args) {
        RedissonClient redissonClient = getRedissonClient();
        RBlockingQueue<String> blockingFairQueue = redissonClient.getBlockingQueue("delay_demo");
        RDelayedQueue<String> delayedQueue = redissonClient.getDelayedQueue(blockingFairQueue);

        for (int i = 0; i < 10; i++) {
            delayedQueue.offer("ZhangSan"+i, 10+i, TimeUnit.SECONDS);
            System.out.println("---订单创建：" + "ZhangSan"+i + "  订单生成时间" + LocalTime.now().format(DateTimeFormatter.ofPattern("mm:ss")));
        }
        delayedQueue.remove("ZhangSan"+5);
        delayedQueue.offer("ZhangSan"+5, 1, TimeUnit.SECONDS);
    }

    private static RedissonClient getRedissonClient() {
        Config config = new Config();
        config.useSingleServer()
                .setAddress("redis://127.0.0.1:6379").setDatabase(1);
        return Redisson.create(config);
    }


}
