package config.filters;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;



//自定义的全局过滤器 （作用是统一鉴权）  又因为这是个全局配置 所以一直都在生效 无论是在product还是order微服务上
//要求：必须实现GatewayFilter, Ordered这两个接口  并实现里面的两个方法
//全局过滤器不需要在application.yml中配置 只需要编写这个类 然后把他放到容器里面即可
//这里是设置只有token为admin的时候 路由的消息才能允许进来
@Slf4j
//@Component
public class AuthGlobalFilter implements GlobalFilter, Ordered {

    //过滤器逻辑
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        //统一鉴权逻辑
        String token = exchange.getRequest().getQueryParams().getFirst("token");
        System.out.println("进入带这个进行比较随便宣布i");

        if(!StringUtils.equals("admin",token)){
            //认证失败
            log.info("认证失败了.......");
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete();
        }
        //放行
        return chain.filter(exchange);
    }

    //标识当前过滤器的优先级，返回值越小，优先级越高
    @Override
    public int getOrder() {
        return 0;
    }
}
