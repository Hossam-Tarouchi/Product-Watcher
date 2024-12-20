package com.imedia24.productWatcher.service.implementation;

import com.imedia24.productWatcher.dao.entity.ProductEntity;
import com.imedia24.productWatcher.dao.model.Product;
import com.imedia24.productWatcher.dao.repository.ProductRepository;
import com.imedia24.productWatcher.service.interfaces.IProductService;
import com.imedia24.productWatcher.util.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class ProductService implements IProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    @Autowired
    public ProductService(ProductRepository productRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }

    @Override
    public Product createProduct(Product product) {
        ProductEntity productEntity = productMapper.toProductEntity(product);
        productEntity = productRepository.save(productEntity);
        return productMapper.toProduct(productEntity);
    }

    @Override
    public Product getProduct(Long sku) {
        return productRepository.findById(sku)
                .map(productMapper::toProduct)
                .orElse(null);
    }

    @Override
    public List<Product> findProductsWithPricesFromLastThreeMonths() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, -3);
        Date threeMonthsAgo = calendar.getTime();
        return this.productRepository.findProductsWithPricesFromLastThreeMonths(threeMonthsAgo).stream()
                .map(productMapper::toProduct)
                .toList();
    }
}
