package com.imedia24.productWatcher.dao.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "product")
@Builder
public class ProductEntity {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long sku;

    @NotNull(message = "Product name can't be null.")
    @NotBlank(message = "Product name can't be empty.")
    private String name;

    @OneToMany(mappedBy = "product")
    private List<PriceEntity> prices;
}
