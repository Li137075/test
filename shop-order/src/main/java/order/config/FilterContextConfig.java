package order.config;



import com.alibaba.csp.sentinel.adapter.servlet.CommonFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//下面的这个类是进行sentinel流控开启的类
//配置这个类是为了使用sentinel的链路限流功能


//@Configuration
//public class FilterContextConfig {
//
//    @Bean
//    public FilterRegistrationBean sentinelFilterRegistration(){
//
//        FilterRegistrationBean registration = new FilterRegistrationBean();
//        registration.setFilter(new CommonFilter());
//        registration.addUrlPatterns("/*");
//
//        // 入口资源关闭聚合
//        registration.addInitParameter(CommonFilter.WEB_CONTEXT_UNIFY, "false");
//        registration.setName("sentinelFilter");
//        registration.setOrder(1);
//
//        return registration;
//    }
//}
