package com.lutfly.dubbo.provider.service;

import org.apache.dubbo.config.annotation.Service;
import org.apache.dubbo.rpc.RpcContext;
import org.apache.dubbo.rpc.RpcException;

/**
 *
 *
 * @author lutong
 * @since 2020/8/18
 */
@Service(version = "${dubbo.application.version}", timeout = 5000, delay = -1)
public class serviceImpl implements IService{
    public String getUserName() {
        RpcContext.getServerContext().setAttachment("bbb","2222");
        throw new RpcException();
//        return "张三";
    }
}
