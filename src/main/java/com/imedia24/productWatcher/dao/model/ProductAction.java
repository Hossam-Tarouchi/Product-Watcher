package com.imedia24.productWatcher.dao.model;

import com.imedia24.productWatcher.util.enums.Action;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ProductAction {
    private Long sku;
    private Action type;
    private String date;
}

