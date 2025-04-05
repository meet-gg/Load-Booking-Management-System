package com.meetg.InternTask.Services;

import com.meetg.InternTask.DTO.BookingRequestDTO;
import com.meetg.InternTask.Entity.BookingStatus;
import com.meetg.InternTask.Entity.LoadStatus;
import org.springframework.http.ResponseEntity;

import java.util.UUID;

public interface BookingServices {
    ResponseEntity<?> createBooking(BookingRequestDTO bookingRequestDTO);
    ResponseEntity<?> bookingFilter(String shipperId, String transporterId, BookingStatus status, String truckType, String productType);
    ResponseEntity<?> getBookingById(UUID bookingId);
    ResponseEntity<?> updateBookingById(UUID bookingId,BookingRequestDTO bookingRequestDTO);
    ResponseEntity<?> deleteBookingById(UUID bookingId);
    ResponseEntity<?> acceptBookingById(UUID bookingId);
    ResponseEntity<?> getBookingByLoadId(UUID loadId);
}
