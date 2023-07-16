package tt;

import domain.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "service-product")
public interface ProductService {

    @RequestMapping("/product/select")
    public Product findByPid(@RequestParam("Pid") Integer Pid);

}
