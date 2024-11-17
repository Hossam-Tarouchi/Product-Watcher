package com.imedia24.productWatcher.util.mapper;

import com.imedia24.productWatcher.dao.entity.PriceEntity;
import com.imedia24.productWatcher.dao.entity.ProductEntity;
import com.imedia24.productWatcher.dao.model.Price;
import org.springframework.stereotype.Component;

@Component
public class PriceMapper {

    public PriceEntity toPriceEntity(Price price, ProductEntity productEntity) {
        return PriceEntity.builder()
                .id(price.getId())
                .price(price.getPrice())
                .product(productEntity)
                .date(price.getDate())
                .build();
    }

    public Price toPrice(PriceEntity priceEntity) {
        return Price.builder()
                .id(priceEntity.getId())
                .price(priceEntity.getPrice())
                .sku(priceEntity.getProduct().getSku())
                .date(priceEntity.getDate())
                .build();
    }
}
