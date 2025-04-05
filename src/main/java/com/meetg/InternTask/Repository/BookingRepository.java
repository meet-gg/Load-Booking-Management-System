package com.meetg.InternTask.Repository;

import com.meetg.InternTask.Entity.Booking;
import com.meetg.InternTask.Entity.BookingStatus;
import com.meetg.InternTask.Entity.LoadStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface BookingRepository extends JpaRepository<Booking, UUID> {
    @Query("""
        SELECT b FROM Booking b
        WHERE (:shipperId IS NULL OR b.load.shipperId = :shipperId)
          AND (:transporterId IS NULL OR b.transporterId = :transporterId)
          AND (:status IS NULL OR b.status = :status)
          AND (:truckType IS NULL OR b.load.truckType = :truckType)
          AND (:productType IS NULL OR b.load.productType = :productType)
    """)
    List<Booking> bookingFilter(
            @Param("shipperId") String shipperId,
            @Param("transporterId") String transporterId,
            @Param("status") BookingStatus status,
            @Param("truckType") String truckType,
            @Param("productType") String productType
    );

    Optional<List<Booking>> findByLoadId(UUID loadId);
}
