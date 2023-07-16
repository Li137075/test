package order.service.fallback;


import domain.Product;
import order.service.ProductService;
import org.springframework.stereotype.Service;

//这个类是feign的接口容错类  他需要实现feign所在的接口，并去实现接口中的所有方法
//一旦feign远程调用出现问题了，就会进入当前类中的同名方法，执行容错逻辑
//@Service
//public class ProductServiceFallBack implements ProductService {
//    @Override
//    public Product findByPid(Integer pid) {
//        //容错逻辑
//        Product product=new Product();
//        product.setPid(-100);
//        product.setPname("远程调用微服务出现异常了，进入了容错逻辑");
//        return product;
//    }
//}
