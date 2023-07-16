package domain;



import lombok.Data;


@Data

public class User {


    private Integer uid; //主键
    private String username;//用户名
    private Integer deposit;//存款
    private Integer spend;//消费金额
}
