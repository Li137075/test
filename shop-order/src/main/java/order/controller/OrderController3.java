package order.controller;


import com.alibaba.fastjson.JSON;
import domain.Order;
import domain.Product;
import domain.User;
import lombok.extern.slf4j.Slf4j;
import order.service.OrderService;
import order.service.ProductService;
import order.service.UserService;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


//这个controller类主要是用来做seta功能的 用来做seta功能的测试
//在Spring中@RestController的作用等同于@Controller + @ResponseBody。
@RestController
@Slf4j
public class OrderController3 {

    //注入订单的service
    @Autowired
    private OrderService orderService;

    @Autowired
    private RestTemplate restTemplate;

    //下面这个是使用alibaba中nacos中的类获得微服务的相关信息
    @Autowired
    private DiscoveryClient discoveryClient;


    //下面这个注入的是加了feign的一个接口 能够达到访问远程服务 就像是访问本地方法一样的效果
    //  调用方远程的Prodcut服务
    @Autowired
    private ProductService productService;

    @Autowired
//  调用远程的user服务
    private UserService userService;

    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    //订单微服务的下单功能   这里为了简单并没有传递用户和数量信息 直接默认为是某一个用户买一件商品
    //出现的问题主要有以下几个：
    //      1.一旦服务提供者的地址信息变化了，我们就不得不去修改服务调用者的java代码
    //      2.一旦服务提供者做了集群，讲无法实现负载均衡
    //      3.一旦微服务变得越来越堵哦，如何来管理这个服务清单就成了问题
//    这里使用的是restful格式的URI，因此这里是使用@PathVariable注解接受参数
    @RequestMapping("/order/prod")
    public Order order(Integer uid,Integer Pid){
        System.out.println(uid);
        System.out.println(Pid);

        System.out.println("进入到这个代码里面了。。。。。。。。");
        //当我们使用restTemplate做服务的调用时会面临   代码可读性不好和编程风格不统一的问题  索引就引入了使用feign进行远程服务调用
        //feign下面集成了ribbon  所以在nacos下使用feign默认就实现了负载均衡的效果  下面这几行代码是使用了feign的代码  在这里就不用使用restTemplate了
        Product product=productService.findByPid(Pid);
        System.out.println("*******************");
        System.out.println(product);
        //下面这里对productService的Feign接口的容错类进行判断
        if(product.getPid()==-100){
            Order order=new Order();
            order.setOid(-100L);
            order.setPname("下单失败");
            return order;
        }


        log.info("接收到{}号商品的下单请求，接下来调用商品微服务查询此商品信息",Pid);

//      下了订单后，用户的存款将会降低
        User user=userService.select(uid);
        int price=product.getPprice();
        System.out.println("++++++++++++++++++++++");
        userService.update(uid,price);

//      下了订单之后，产品的库存将会降低
        int id=product.getPid();
        int stock=product.getStock();
        stock=stock-1;
        productService.update_stock(id,stock);


        //下单（创建订单）这里是利用已经有的信息 组装出一个订单的类
        Order order=new Order();
        //下面是用户的一些属性,这里为了简便 直接进行模拟了
        order.setUid(uid);
        String name=user.getUsername();
        order.setUsername(name);
        //下面是商品的一些信息
        order.setPid(Pid);
        order.setPname(product.getPname());
        order.setPprice(product.getPprice());
        //下面是产品的数量 这里为了简单也直接模拟了
        order.setNumber(1);
        int a=1/0;
//      我们将这个创建的订单类加入到订单的数据库中
        orderService.createrOrder(order);

        log.info("创建订单成功，订单信息为{}",JSON.toJSONString(order));

        //下面这一行代码指的是向rocketmq消息队列中放入消息
        //向mq中投递一个下单成功的消息
        //参数一：指定topic
        //参数二：指定消息体
        rocketMQTemplate.convertAndSend("order-topic",order);
        return order;
    }

// 这里是使用的restful格式的URI
    @RequestMapping("/order/delete/{oid}")
    public void deleteOrder(@PathVariable("oid") Integer oid){
        orderService.deleteOrder(oid);
    }


    //测试该微服务下的高并发场景  我们通过使用jmeter模拟了很多次的请求  请求的路径是上面那个方法 有可能就会导致下面的这个message方法访问不到  这就是高并发的缺点
    @RequestMapping("/order/message")
    public String message(){
        return "测试高并发";
    }

}
