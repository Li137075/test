package product.service.impl;

import domain.Product;
import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import product.mapper.ProductMapper;
import product.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductMapper productMapper;

    @Override
    public Product findByPid(Integer pid) {
        System.out.println("service是完好的");
        return productMapper.findByPid(pid);
    }

    @Override
    public void insert(Product product) {
        productMapper.insert(product);
    }

    @Override
    public void update(Product product) {
        productMapper.update(product);
    }

    @GlobalTransactional
    @Override
    public void update_stock(Integer id, Integer stock) {
        productMapper.update_stock(id,stock);
    }

    @Override
    public void delete(Integer pid) {
        productMapper.delete(pid);
    }
}
