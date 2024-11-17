package com.imedia24.productWatcher.util.mapper;

import com.imedia24.productWatcher.dao.entity.ProductEntity;
import com.imedia24.productWatcher.dao.model.Price;
import com.imedia24.productWatcher.dao.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProductMapper {

    private final PriceMapper priceMapper;

    @Autowired
    public ProductMapper(PriceMapper priceMapper) {
        this.priceMapper = priceMapper;
    }

    public ProductEntity toProductEntity(Product product) {
        return ProductEntity.builder()
                .sku(product.getSku())
                .name(product.getName())
                .build();
    }

    public Product toProduct(ProductEntity productEntity) {
        List<Price> prices = new ArrayList<>();
        if (productEntity.getPrices() != null) {
            prices = productEntity.getPrices().stream().map(priceMapper::toPrice).toList();
        }
        return Product.builder()
                .sku(productEntity.getSku())
                .name(productEntity.getName())
                .prices(prices)
                .build();
    }
}
