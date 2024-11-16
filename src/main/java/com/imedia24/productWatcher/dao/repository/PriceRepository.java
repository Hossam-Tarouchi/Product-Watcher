package com.imedia24.productWatcher.dao.repository;

import com.imedia24.productWatcher.dao.entity.PriceEntity;
import com.imedia24.productWatcher.dao.model.Price;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PriceRepository extends JpaRepository<PriceEntity, Long> {
}
