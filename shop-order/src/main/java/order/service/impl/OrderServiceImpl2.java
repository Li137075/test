package order.service.impl;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import domain.Order;
import lombok.extern.slf4j.Slf4j;
import order.mapper.OrderMapper;
import order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//这个类是定以当sentinel发生了流控异常或者是降级的时候 的处理方法
//而ExceptionHandlerPage.java类是定义当发生这些异常的时候  返回的页面结果
//ExceptionHandlerPage.java是处理限流降级这些异常的时候  返回的页面结果
//而这里是分了sentinel的异常和throwable的异常

//@Service
@Slf4j
public class OrderServiceImpl2  {

    int i=0;

    //这里的@SentinelResource是定义一个资源   message  是指定的资源名称
    //定义当资源内部发生异常的时候的处理逻辑
    //@SentinelResource中的blockHandler  定义了当资源内发生了BlockException（捕获的是sentinel的限流 降级 热点 授权等异常）异常时应该进入的方法
    //fallback定义的是当资源内部发生了Throwable时应该进入的方法
    //这里的@SentinelResource加在一个资源上 是指定该资源的fallback和blockHandler的方法
    @SentinelResource(
            value = "message",
            blockHandlerClass = OrderServiceImpl2BlockHandler.class,
            blockHandler = "blockHandler",
            fallbackClass = OrderServiceImpl2Fallback.class,
            fallback="fallback"
    )

    public String message() {
//        i++;
//        if(i%3==0){
//            throw new RuntimeException();
//        }
        return "message";
    }


    //下面定义的这两个异常处理的方法是和业务处理方法对应的，因为需要传入业务处理方法的参数，所以会显得该类十分的臃肿
    //因此，我们可以将这两个异常处理方法  另写两个类这样就会减少代码的臃肿

    //blockHandler
    //要求：
    //1、当前方法的返回值和参数要和原方法(这个原方法指的是定义资源下的那个方法)一致
    //2、但是允许在参数列表的最后加入一个参数BlockException，用来接收原方法中发生的异常
    public String blockHandler(BlockException e){
        //自定义异常处理逻辑
        log.error("触发了BlockException，内容为{}",e);
        return "BlockException";
    }

    //fallback
    //要求：
    //1、当前方法的返回值和参数要和原方法(这个原方法指的是定义资源下的那个方法)一致
    //2、但是允许在参数列表的最后加入一个参数BlockException，用来接收原方法中发生的异常
    public String fallback(Throwable e){
        //自定义异常处理逻辑
        log.error("触发了Throwable，内容为{}",e);
        return "Throwable";
    }
}
