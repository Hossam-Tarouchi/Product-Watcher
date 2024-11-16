package com.imedia24.productWatcher.dao.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Price {
    private Long id;
    private float price;
    private Date date;
    private Long sku;
}
