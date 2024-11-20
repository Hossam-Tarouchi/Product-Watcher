package com.imedia24.productWatcher.service.interfaces;

import com.imedia24.productWatcher.dao.model.ProductAction;

import java.util.List;
import java.util.Map;

public interface IRabbitMQService {

    void publish(Map<String, List<ProductAction>> actions);

}
