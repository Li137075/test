package order.service.impl;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import lombok.extern.slf4j.Slf4j;

//这里是OrderServiceImpl2BlockHandler对应的BlockException处理的类

@Slf4j
public class OrderServiceImpl2BlockHandler {


    //blockHandler
    //要求：
    //1、当前方法的返回值和参数要和原方法(这个原方法指的是定义资源下的那个方法)一致
    //2、但是允许在参数列表的最后加入一个参数BlockException，用来接收原方法中发生的异常
    public static String blockHandler(BlockException e){
        //自定义异常处理逻辑
        log.error("触发了BlockException，内容为{}",e);
        return "BlockException";
    }
}
