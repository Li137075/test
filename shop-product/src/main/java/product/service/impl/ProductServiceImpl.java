package product.service.impl;

import domain.Product;
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
        return productMapper.findById(pid);
    }
}
