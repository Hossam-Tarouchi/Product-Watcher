package com.imedia24.productWatcher.dao.repository;

import com.imedia24.productWatcher.dao.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {

    @Query("SELECT p FROM ProductEntity p LEFT JOIN FETCH p.prices pr WHERE pr.date >= :threeMonthsAgo")
    List<ProductEntity> findProductsWithPricesFromLastThreeMonths(Date threeMonthsAgo);
}
