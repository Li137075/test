package domain;






import lombok.Data;

import java.io.Serializable;


@Data
public class Product{

    private Integer pid;//主键
    private String pname;//商品名称
    private Integer pprice;//商品价格
    private Integer stock;//库存



}
