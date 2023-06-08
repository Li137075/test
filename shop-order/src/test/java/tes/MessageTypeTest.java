package tes;


import order.OrderApplication;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = OrderApplication.class)
public class MessageTypeTest {

    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    //下面是rocketmq的关于可靠同步消息的发送
    //同步发送是指消息发送发出消息后，会在收到接收方发回响应之后才发下一个数据包的通讯方式
    @Test
    public void testSyncSend(){
        //该方法的第一个参数是topic  第二个参数是消息体 第三个参数是超时时间
        //关于消息的tag的设计，允许topic：tag的方式设置消息的tag
        SendResult result = rocketMQTemplate.syncSend("test-topic-1:tag", "这是一条同步消息", 10000);
        System.out.println(result);
    }

    //异步发送是指发送方发出数据后，不等接收方发回邮件，接着发送下个数据包的通讯方式   发送方通过回调接口接收服务器响应，并对响应结果进行处理
    @Test
    public void testAsyncSend() throws InterruptedException{
        //该方法的第一个参数是topic  第二个参数是消息体 第三个参数是回调
        rocketMQTemplate.asyncSend("test-topic-1", "这是一条异步消息", new SendCallback() {

            //成功响应的回调
            @Override
            public void onSuccess(SendResult sendResult) {
                System.out.println(sendResult);
            }

            //异常响应的回调
            @Override
            public void onException(Throwable throwable) {
                System.out.println(throwable);
            }
        });
        System.out.println("==============================");
        //让进程休眠，确保回调函数能够执行
        Thread.sleep(300000000);
    }

    //单向消息
    @Test
    public void testOneWay(){
        rocketMQTemplate.sendOneWay("test-topic-1", "这是一条单向消息");
    }

}
