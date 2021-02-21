package com.lutfly.guava.demo;

import com.google.common.util.concurrent.RateLimiter;
import com.lmax.disruptor.dsl.Disruptor;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 *
 *
 * @author lutong
 * @since 2020/12/22
 */
public class DisruptorDemo {


    public static void main(String[] args) throws InterruptedException {
//        Disruptor disruptor = new Disruptor();
        ABQ();
        Thread.sleep(10000000000L);
    }

    private static void ABQ() throws InterruptedException {
         RateLimiter put = RateLimiter.create(5);
         RateLimiter take = RateLimiter.create(4);
        ArrayBlockingQueue<Integer> integers = new ArrayBlockingQueue<Integer>(10);
        new Thread(()-> {
            for (int i = 0; i < 10; i++) {
                try {
//                    put.acquire();
                    integers.put(i);
                    System.out.println(integers);
//                    System.out.println("put "+ i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            try {
                integers.take();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        Thread.sleep(TimeUnit.SECONDS.toMillis(5));
//        new Thread(()-> {
//            for (int i = 1; i < 100; i++) {
//                try {
//                    take.acquire();
//                    Integer i1 = integers.take();
//                    System.out.println(integers);
//
////                    System.out.println("take "+ i1);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        }).start();
    }

    private static void disruptor(){
    }

}




class Queue {
    private Long[] data;
    private int size = 0, head = 0, tail = 0;
    public Queue(int size) {
        this.data = new Long[size];
        this.size = size;
    }

    public boolean add(Long element) {
        if ((tail + 1) % size == head) return false;
        data[tail] = element;
        tail = (tail + 1) % size;
        return true;
    }

    public Long poll() {
        if (head == tail) return null;
        long ret = data[head];
        head = (head + 1) % size;
        return ret;
    }
}

class Producer {
    private Queue queue;
    public Producer(Queue queue) {
        this.queue = queue;
    }

    public void produce(Long data) throws InterruptedException {
        while (!queue.add(data)) {
            Thread.sleep(100);
        }
    }
}

class Consumer {
    private Queue queue;
    public Consumer(Queue queue) {
        this.queue = queue;
    }

    public void comsume() throws InterruptedException {
        while (true) {
            Long data = queue.poll();
            if (data == null) {
                Thread.sleep(100);
            } else {
                // TODO:...消费数据的业务逻辑...
            }
        }
    }
}