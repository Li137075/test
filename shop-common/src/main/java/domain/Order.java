package domain;

import lombok.Data;

@Data
public class Order {

    private Long oid;//

//    和用户相关的两个属性
    private Integer uid;//用户id
    private String username;//用户名

//    和商品相关的三个属性
    private Integer pid;//商品id
    private String pname;//商品名称
    private Integer pprice;//商品单价

//   数量
    private Integer number;//购买数量

}
