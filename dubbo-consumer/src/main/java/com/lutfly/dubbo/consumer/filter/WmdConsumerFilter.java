package com.lutfly.dubbo.consumer.filter;

import org.apache.dubbo.common.extension.Activate;
import org.apache.dubbo.rpc.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static org.apache.dubbo.common.constants.CommonConstants.CONSUMER;

@Activate(group = CONSUMER)
public class WmdConsumerFilter implements Filter {

    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
//        Object object = ContextHandler.get(Wmd.WMD_KEY);
//        if (!Objects.isNull(object)) {
//            Wmd wmd = (Wmd) object;
//            Map<String, String> map = new HashMap<>(4);
//            map.put(Wmd.VID, wmd.getVid());
//            map.put(Wmd.VSQ, wmd.getVsq());
//            map.put(Wmd.EID, wmd.getEid());
//            RpcContext.getContext().setAttachments(map);
//        }
        return invoker.invoke(invocation);
    }
}