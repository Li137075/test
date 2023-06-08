package config.predicates;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cloud.gateway.handler.predicate.AbstractRoutePredicateFactory;
import org.springframework.cloud.gateway.handler.predicate.BeforeRoutePredicateFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

//这是一个自定义的路由断言工厂类 要求有两个
//1 名字必须是 配置+RoutePredicateFactory
//2 必须继承AbstractRoutePredicateFactory<配置类>
//@Component
public class AgeRoutePredicateFactory extends AbstractRoutePredicateFactory<AgeRoutePredicateFactory.Config> {

//    构造函数
    public AgeRoutePredicateFactory() {
        super(AgeRoutePredicateFactory.Config.class);
    }

//    读取配置文件(application.yml)中的参数值 并将他赋值到配置类中的属性上
    public List<String> shortcutFieldOrder() {
        //这个位置中的顺序必须跟配置文件中的值的顺序对应
        return Arrays.asList("minAge","maxAge");
    }

    //断言逻辑   这里的参数config指的是能够获取application.yml中参数的一个变量
    public Predicate<ServerWebExchange> apply(AgeRoutePredicateFactory.Config config) {
        return new Predicate<ServerWebExchange>() {
            @Override
            public boolean test(ServerWebExchange serverWebExchange) {
                //1、 接收前端传入的age参数
                String ageStr=serverWebExchange.getRequest().getQueryParams().getFirst("age");

                //2、 先判断是否为空
                if (StringUtils.isNotEmpty(ageStr)){
                    //3、 如果不为空，再进行路由逻辑判断
                    int age=Integer.parseInt(ageStr);
                    if(age < config.getMaxAge() && age> config.getMinAge()){
                        return true;
                    }else{
                        return false;
                    }
                }
                return false;

            }
        };
    }

    @Data
    @NoArgsConstructor
//配置类，用于接收配置文件中的对应参数
    public static class Config {
        private int minAge;
        private int maxAge;
    }
}
