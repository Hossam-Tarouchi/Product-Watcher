package com.imedia24.productWatcher.service.implementation;

import com.imedia24.productWatcher.dao.entity.PriceEntity;
import com.imedia24.productWatcher.dao.model.Price;
import com.imedia24.productWatcher.dao.repository.PriceRepository;
import com.imedia24.productWatcher.service.interfaces.IPriceService;
import com.imedia24.productWatcher.util.mapper.PriceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PriceService implements IPriceService {

    private final int PAGE_SIZE = 2;

    private final PriceRepository priceRepository;
    private final PriceMapper priceMapper;

    @Autowired
    public PriceService(PriceRepository priceRepository, PriceMapper priceMapper) {
        this.priceRepository = priceRepository;
        this.priceMapper = priceMapper;
    }

    @Override
    public List<Price> getPaginatedPrices(int page) {
        return priceRepository.findAll(PageRequest.of(page - 1, PAGE_SIZE, Sort.by("date"))).stream()
                .map(priceMapper::toPrice)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    @Override
    public Price createPrice(Price price) {
        PriceEntity priceEntity = priceMapper.toPriceEntity(price);
        priceEntity = priceRepository.save(priceEntity);
        return priceMapper.toPrice(priceEntity);
    }
}
