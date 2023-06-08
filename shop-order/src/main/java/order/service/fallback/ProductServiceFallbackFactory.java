package order.service.fallback;

import domain.Product;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import order.service.ProductService;
import org.springframework.stereotype.Service;

@Slf4j
@Service
//这里的这个泛型指的是为哪个接口产生容错类
//这里的ProductServiceFallbackFactory和上面的那个 ProductServiceFallBack的区别在于 下面的这个类能够指示具体是哪里出现了问题
public class ProductServiceFallbackFactory implements FallbackFactory<ProductService> {


    //这里的throwable就是feign在调用过程中产生的异常
    @Override
    public ProductService create(Throwable throwable) {
        return new ProductService() {
            @Override
            public Product findByPid(Integer pid) {
                //这里是将throwable这个feign调用时产生的异常打印出来
                log.error("{}",throwable);
                //容错逻辑
                Product product=new Product();
                product.setPid(-100);
                product.setPname("远程调用微服务出现异常了，进入了容错逻辑");
                return product;
            }
        };
    }
}
