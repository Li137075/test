package order.controller;


import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.fastjson.JSON;
import domain.Order;
import domain.Product;
import lombok.extern.slf4j.Slf4j;
import order.service.OrderService;
import order.service.ProductService;
import order.service.impl.OrderServiceImpl2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

//我们这里的ordercontroller2是用来测试sentinel的保护的
//在Spring中@RestController的作用等同于@Controller + @ResponseBody。
//@RestController
@Slf4j
public class OrderController2 {

    @Autowired
    private OrderServiceImpl2 orderServiceImpl2;

//    int i=0;

    //这里进行测试sentinel是怎么进行容断 降级的
    @RequestMapping("/order/message1")
    public String message1(){

        //这里是为了模拟sentinel降级中的按照异常比例进行降级 这里的一场比例是1/3
//        i++;
//        if(i%3==0){
//            throw new RuntimeException();
//        }

//        orderServiceImpl2.message();
        return "message1";
    }

    @RequestMapping("/order/message2")
    public String message2(){
//        System.out.println("............................................");
        return orderServiceImpl2.message();
    }


    //下面的这几行代码是为了测试sentinel的热点规则（热点规则是一种更细粒度的流控规则  他将流控规则具体到参数上）
    @RequestMapping("/order/message3")
    @SentinelResource("message3")
    public String message3(String name,Integer age){
        return "message3" + name +age;
    }

    @RequestMapping("/order/message")
    public String message(){
        return orderServiceImpl2.message();
    }

}
