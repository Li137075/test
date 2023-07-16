package product.controller;
import com.alibaba.fastjson.JSON;
import domain.Product;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import product.service.ProductService;
//在Spring中@RestController的作用等同于@Controller + @ResponseBody。
@RestController
@Slf4j
public class ProductController {

    @Autowired
    private ProductService productService;

//  这里是使用原始方式进行URI查询的 URI为http://localhost:8083/product/select/?id=1 如果向用参数接收 要用@RequestParam参数   或者在方法里面直接设置参数，就能接收URI的值
//  也可以使用Restful的方式词是为/product/select/{id}   uri为http://localhost:8083/product/select/1
//  商品信息查询
    @RequestMapping("/product/select")
    public Product findByPid(Integer Pid){

        log.info("接下来要进行{}号商品的查询",Pid);
        Product product=productService.findByPid(Pid);
        log.info("商品信息查询成功，内容为{}", JSON.toJSONString(product));
        return product;
    }

//  这个的URI是http://localhost:8083/product/insert/?id=10&name=cdshj&price=100&stock=100
//  向数据库中加入新的商品
    @RequestMapping("/product/insert")
    public void insert(Integer id,String name,Integer price,Integer stock){
        System.out.println("ncdsakjncwsnvbkrebij");
        Product product=new Product();
        product.setPid(id);
        product.setPname(name);
        product.setPprice(price);
        product.setStock(stock);
        productService.insert(product);
    }

// 这里的URI为http://localhost:8083/product/update/?id=1&name=%E5%B0%8F%E7%B1%B3&price=10&stock=100
    @RequestMapping("/product/update")
    public void update(Integer id,String name,Integer price,Integer stock){
        Product product=new Product();
        product.setPid(id);
        product.setPname(name);
        product.setPprice(price);
        product.setStock(stock);
        productService.update(product);
    }

    @RequestMapping("/product/update_stock")
    public void update_stock(Integer id,Integer stock){
        productService.update_stock(id,stock);
    }

//注意 如果我们使用传统方式的URI，使用接收参数的注解是@RequestParam，例如blogs?blogId=1    但是如果使用restful方式的URI使用的是@PathVariable，例如/blogs/1
    @RequestMapping("/product/delete")
    public void delete(Integer id){
        productService.delete(id);
    }



}
