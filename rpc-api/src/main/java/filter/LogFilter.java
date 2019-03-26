package filter;

import com.alibaba.dubbo.rpc.*;

public class LogFilter implements Filter {


    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {


        long timeTake;
        long beginTime = System.currentTimeMillis();

        Result res = null;
        try {

            res = invoker.invoke(invocation);
            return res;

        }catch (RpcException e){

            throw e;
        }finally {

            long endTime = System.currentTimeMillis();
            timeTake = endTime - beginTime;
            if(RpcContext.getContext().isProviderSide()){

                System.out.println("提供方耗时："+timeTake);
            }
            else if(RpcContext.getContext().isConsumerSide()){
                System.out.println("消耗方耗时："+timeTake);
            }else{
                System.out.println("耗时："+timeTake);
            }

            // KakfaSender.send();
        }


    }
}
