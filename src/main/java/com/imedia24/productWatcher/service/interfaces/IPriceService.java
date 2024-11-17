package com.imedia24.productWatcher.service.interfaces;

import com.imedia24.productWatcher.dao.model.Price;
import com.imedia24.productWatcher.util.exception.ProductDoesntExistException;

import java.util.List;

public interface IPriceService {

    Price createPrice(Price price) throws ProductDoesntExistException;
    List<Price> getPaginatedPrices(int page);

}
