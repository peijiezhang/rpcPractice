package com.zpj.filter;


import com.alibaba.dubbo.rpc.*;
import ipTools.IpWhiteList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class AuthorityFilter  implements Filter {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthorityFilter.class);

   private IpWhiteList ipWhiteList;



    private List<String>  ips;

    //dubbo通过setter方式自动注入
    public void setIpWhiteList(IpWhiteList ipWhiteList) {
        this.ipWhiteList = ipWhiteList;
    }
    public AuthorityFilter() {

        ips = new ArrayList<String>();
        ips.add("172.16.1.46");

    }

    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        System.out.println("进入了白名单拦截器");
        if (!ipWhiteList.isEnabled()) {
            LOGGER.debug("白名单禁用");
            System.out.println("白名单禁用");
            return invoker.invoke(invocation);
        }

     /*   long startTime = System.currentTimeMillis();
        long takeTime = 0l;
        if ( RpcContext.getContext().isConsumerSide()){



        }else {

        }*/

        String clientIp = RpcContext.getContext().getRemoteHost();
        String name = invoker.getInterface().getName();
        Object[] args = invocation.getArguments();
        String method = invocation.getMethodName();
        String prefix = "日志："+name+"."+method;
        LOGGER.info(prefix+" 入参=>"+args);

       // LOGGER.debug("访问ip为{}", clientIp);
        List<String> allowedIps = ipWhiteList.getAllowIps();
        // zipkin
        LOGGER.info("调用成功");
        System.out.println("调用成功");
        if (allowedIps.contains(clientIp)) {
            return invoker.invoke(invocation);
        } else {
            return new RpcResult();
        }
    }
}
