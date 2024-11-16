package com.imedia24.productWatcher.util.mapper;

import com.imedia24.productWatcher.dao.entity.PriceEntity;
import com.imedia24.productWatcher.dao.model.Price;
import org.springframework.stereotype.Component;

@Component
public class PriceMapper {
    public PriceEntity toPriceEntity(Price price) {
        return PriceEntity.builder()
                .id(price.getId())
                .price(price.getPrice())
                .sku(price.getSku())
                .date(price.getDate())
                .build();
    }

    public Price toPrice(PriceEntity priceEntity) {
        return Price.builder()
                .id(priceEntity.getId())
                .price(priceEntity.getPrice())
                .sku(priceEntity.getSku())
                .date(priceEntity.getDate())
                .build();
    }
}
