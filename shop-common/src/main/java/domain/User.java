package domain;



import lombok.Data;


@Data

public class User {


    private Integer uid; //主键
    private String username;//用户名
    private String password;//密码
    private String telephone;//手机号
}
