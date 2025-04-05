package com.meetg.InternTask.Repository;

import com.meetg.InternTask.Entity.Load;
import com.meetg.InternTask.Entity.LoadStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface LoadRepository extends JpaRepository<Load, UUID> {
    @Query("""
    SELECT l FROM Load l
    WHERE (:shipperId IS NULL OR l.shipperId = :shipperId)
      AND (:truckType IS NULL OR l.truckType = :truckType)
      AND (:productType IS NULL OR l.productType = :productType)
      AND (:status IS NULL OR l.status = :status)
    """)
    Optional<List<Load>> filterLoads(
            @Param("shipperId") String shipperId,
            @Param("truckType") String truckType,
            @Param("productType") String productType,
            @Param("status") LoadStatus status
    );

}
