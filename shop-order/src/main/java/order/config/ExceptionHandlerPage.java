package order.config;

//这个类是为了配置sentinel的不同的流控  降级  热点  授权返回的页面 使每一个都有一种返回页面 能够很好地观察到到底是什么配置 导致的流控异常

import com.alibaba.csp.sentinel.adapter.servlet.callback.UrlBlockHandler;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeException;
import com.alibaba.csp.sentinel.slots.block.flow.FlowException;
import com.alibaba.fastjson.JSON;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


//自定义sentinel的异常返回页面
@Component
public class ExceptionHandlerPage implements UrlBlockHandler {

    @Override
    public void blocked(HttpServletRequest request, HttpServletResponse response, BlockException e) throws IOException {
        response.setContentType("application/json;charset=utf-8");

        ResponseData responseData=null;

        if(e instanceof FlowException){
            responseData=new ResponseData(-1,"接口被限流了。。。。。。。。");
        }else if(e instanceof DegradeException){
            responseData=new ResponseData(-2,"接口被降级了。。。。。。。。");
        }

        response.getWriter().write(JSON.toJSONString(responseData));
    }
}


//这里这个data的注解可以省去下面类中的get set方法
@Data
//下面这个注解的意思是引入全参构造 不用在下面的类中添加构造器了
@AllArgsConstructor
//下面这个注解是无参构造
@NoArgsConstructor
class ResponseData{
    private int code;
    private String message;
}
