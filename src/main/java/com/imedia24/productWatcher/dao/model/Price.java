package com.imedia24.productWatcher.dao.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Price implements Comparable<Price> {

    private Long id;

    @NotNull(message = "price name can't be null.")
    private float price;

    @JsonFormat(pattern = "dd/MM/yyyy", timezone = "Europe/Paris")
    private Date date;

    private Long sku;

    @Override
    public int compareTo(Price price) {
        return this.date.compareTo(price.getDate());
    }
}
