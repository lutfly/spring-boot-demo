package com.lutfly.dubbo.comsumer;

import com.lutfly.dubbo.consumer.DubboConsumerApplication;
import com.lutfly.dubbo.consumer.IConsumer;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = DubboConsumerApplication.class)
public class UserTest {

    @Autowired
    IConsumer iConsumer;


    @Test
    public void test(){
        iConsumer.getUserName();
    }
}
