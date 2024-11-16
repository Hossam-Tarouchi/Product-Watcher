package com.imedia24.productWatcher.util.mapper;

import com.imedia24.productWatcher.dao.entity.ProductEntity;
import com.imedia24.productWatcher.dao.model.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

    public ProductEntity toProductEntity(Product product) {
        return ProductEntity.builder()
                .sku(product.getSku())
                .name(product.getName())
                .build();
    }

    public Product toProduct(ProductEntity productEntity) {
        return Product.builder()
                .sku(productEntity.getSku())
                .name(productEntity.getName())
                .build();
    }
}
