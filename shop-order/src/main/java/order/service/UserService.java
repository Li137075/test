package order.service;

import domain.User;
//import order.service.fallback.ProductServiceFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(
        value = "service-user"
)
public interface UserService {

    @RequestMapping("/user/select")
    User select(@RequestParam("uid") Integer uid);

    @RequestMapping("/user/update")
    void update(@RequestParam("uid") Integer uid,@RequestParam("deposit") Integer deposit);

}
