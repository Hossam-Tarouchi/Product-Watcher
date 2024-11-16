package com.imedia24.productWatcher.service.interfaces;

import com.imedia24.productWatcher.dao.model.Product;

public interface IProductService {

    public Product createProduct(Product product);

    public Product getProduct(Long sku);

}
