package tt.controller;

import domain.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import tt.ProductService;

import java.util.Date;

@RestController
@RequestMapping("/redis")
public class RedisController {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private ProductService productService;


    @RequestMapping("/get")
    public String testRedis(@RequestParam("id") Integer id){
        String sid=id+"";
        if(redisTemplate.hasKey(sid)){
            System.out.println("*****************************");
            String res=(String) redisTemplate.opsForValue().get(sid);
            System.out.println(res);
            return res;
        }else{
            Product product=productService.findByPid(id);
            //设置值到redis
            redisTemplate.opsForValue().set(sid, product.toString());
            //从redis中获取值
            String res=(String) redisTemplate.opsForValue().get(sid);
            System.out.println(res);
            return res;
        }
    }
}
