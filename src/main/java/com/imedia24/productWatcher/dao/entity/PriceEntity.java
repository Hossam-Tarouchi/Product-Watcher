package com.imedia24.productWatcher.dao.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "price")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PriceEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private float price;
    private Date date;
    private Long sku;
}
