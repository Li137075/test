package product.mapper;


import domain.Product;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ProductMapper{

    Product findById(Integer pid);

}
