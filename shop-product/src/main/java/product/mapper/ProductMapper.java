package product.mapper;


import domain.Product;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ProductMapper{

    Product findByPid(Integer pid);

    void insert(Product product);

    void update(Product product);

    void delete(Integer pid);

    void update_stock(Integer id,Integer stock);
}
