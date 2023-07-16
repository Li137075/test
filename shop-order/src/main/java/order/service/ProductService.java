package order.service;


import domain.Product;
//import order.service.fallback.ProductServiceFallBack;
//import order.service.fallback.ProductServiceFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

//value用于指定调用nacos下的哪个微服务  这里的service-product就是相当于访问到这个微服务  下面的方法上的URL都是微服务里的对应路径
//这里相当于是拿到了service-product这个微服务
//fallback用于指定当前fegin接口的容错类
@FeignClient(
        value = "service-product"
//        fallback = ProductServiceFallBack.class,
//        fallbackFactory = ProductServiceFallbackFactory.class
)
public interface ProductService {

    //@FeignClient的接口上的value  +  @RequestMapping的value值  其实就是完整的请求地址    这里是和springmvc一样的
    @RequestMapping("/product/select")
    public Product findByPid(@RequestParam("Pid") Integer Pid);

//    @RequestMapping("/product/update")
//    void update(Integer id,String name,Integer price,Integer stock);

//  对于方法里面的参数 如果不加RequestParam注解 会报java.lang.IllegalStateException: Method has too many Body parameters错误
//  所以我们应该加上@RequestParam注解
    @RequestMapping(value = "/product/update_stock",method = RequestMethod.POST)
    void update_stock(@RequestParam("id") Integer id,@RequestParam("stock") Integer stock);
}
