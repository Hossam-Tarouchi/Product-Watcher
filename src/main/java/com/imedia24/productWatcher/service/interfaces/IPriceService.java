package com.imedia24.productWatcher.service.interfaces;

import com.imedia24.productWatcher.dao.model.Price;

import java.util.List;

public interface IPriceService {

    public Price createPrice(Price price);
    public List<Price> getPaginatedPrices(int page);

}
