package com.imedia24.productWatcher.service.implementation;

import com.imedia24.productWatcher.dao.entity.PriceEntity;
import com.imedia24.productWatcher.dao.entity.ProductEntity;
import com.imedia24.productWatcher.dao.model.Price;
import com.imedia24.productWatcher.dao.repository.PriceRepository;
import com.imedia24.productWatcher.dao.repository.ProductRepository;
import com.imedia24.productWatcher.service.interfaces.IPriceService;
import com.imedia24.productWatcher.util.constant.ResponseMessage;
import com.imedia24.productWatcher.util.exception.ProductDoesntExistException;
import com.imedia24.productWatcher.util.mapper.PriceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PriceService implements IPriceService {

    private final int PAGE_SIZE = 2;

    private final PriceRepository priceRepository;
    private final ProductRepository productRepository;
    private final PriceMapper priceMapper;

    @Autowired
    public PriceService(PriceRepository priceRepository, ProductRepository productRepository, PriceMapper priceMapper) {
        this.priceRepository = priceRepository;
        this.productRepository = productRepository;
        this.priceMapper = priceMapper;
    }

    @Override
    public List<Price> getPaginatedPrices(int page) {
        return priceRepository.findAll(PageRequest.of(page - 1, PAGE_SIZE, Sort.by(Sort.Direction.DESC,"date"))).stream()
                .map(priceMapper::toPrice)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    @Override
    public Price createPrice(Price price) throws ProductDoesntExistException {
        Optional<ProductEntity> productEntity = productRepository.findById(price.getSku());
        if (productEntity.isPresent()) {
            PriceEntity priceEntity = priceRepository.save(priceMapper.toPriceEntity(price, productEntity.get()));
            return priceMapper.toPrice(priceEntity);
        }
        throw new ProductDoesntExistException(ResponseMessage.PRODUCT_NOT_FOUND);
    }
}
