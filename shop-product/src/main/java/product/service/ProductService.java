package product.service;

import domain.Product;

public interface ProductService {

//  根据pid查询商品信息
    Product findByPid(Integer pid);

    void insert(Product product);

    void update(Product product);

    void delete(Integer pid);

    void update_stock(Integer id,Integer stock);
}
