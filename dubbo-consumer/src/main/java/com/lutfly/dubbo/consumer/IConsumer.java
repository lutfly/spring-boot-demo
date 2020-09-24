package com.lutfly.dubbo.consumer;

import com.lutfly.dubbo.provider.service.IService;
import org.apache.dubbo.config.annotation.Reference;
import org.apache.dubbo.rpc.RpcContext;
import org.springframework.stereotype.Controller;

@Controller
public class IConsumer {

    @Reference(version = "1.0.0", retries = -1)
    private IService iService;

    public void getUserName(){
//        RpcContext.getContext().setAttachment("aaa","123");
        String userName = iService.getUserName();
        System.out.println(userName);
    }
}
