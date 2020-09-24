package com.lutfly.dubbo.consumer;

import org.apache.dubbo.rpc.RpcContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

/**
 *
 *
 * @author lutong
 * @since 2020/8/18
 */
@Configuration
public class UserStart implements CommandLineRunner {

    @Autowired
    IConsumer iConsumer;

    @Override
    public void run(String... args) {

        iConsumer.getUserName();
        Map<String, String> attachments = RpcContext.getServerContext().getAttachments();
        System.out.println(attachments);
    }
}
