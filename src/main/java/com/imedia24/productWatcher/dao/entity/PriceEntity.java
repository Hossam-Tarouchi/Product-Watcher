package com.imedia24.productWatcher.dao.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
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
    @JsonFormat(pattern = "dd/MM/yyyy", timezone = "Europe/Paris")  // Explicitly set timezone
    private float price;
    private Date date;
    private Long sku;
}
