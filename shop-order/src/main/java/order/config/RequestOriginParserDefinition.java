package order.config;

import com.alibaba.csp.sentinel.adapter.servlet.callback.RequestOriginParser;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;

//这个类是用来做sentinel的授权规则的，他的作用是区分请求的来源   就是为了区分请求是来源于哪里 是来源于pc还是app  方便接下来做授权规则


//@Component
//public class RequestOriginParserDefinition implements RequestOriginParser {
//
//    //定义区分来源：本质作用是通过request域获取来源标识（比如来源于APP 还是PC等等）
//    //然后交给授权规则中的流控应用位置进行匹配这个来源  设置是否进行授权
//    @Override
//    public String parseOrigin(HttpServletRequest request) {
//        String serviceName= request.getParameter("serviceName");
//        if(StringUtils.isEmpty(serviceName)){
//            throw new RuntimeException("serviceName is not empty");
//        }
//        return serviceName;
//    }
//}
