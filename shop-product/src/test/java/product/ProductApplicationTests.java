package product;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;


@SpringBootTest(properties = "workerClassName=A")
@RunWith(SpringJUnit4ClassRunner.class)
public class ProductApplicationTests {

    @Autowired
    DataSource dataSource;
    @Test
    public void contextLoads() throws Exception{
        System.out.println(dataSource.getLoginTimeout());
        System.out.println("获得的数据库链接为：" + dataSource.getConnection());
    }
}
