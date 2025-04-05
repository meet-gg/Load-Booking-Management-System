package com.meetg.InternTask.Controller;

import com.meetg.InternTask.DTO.BookingRequestDTO;
import com.meetg.InternTask.Entity.BookingStatus;
import com.meetg.InternTask.Entity.LoadStatus;
import com.meetg.InternTask.Services.BookingServices;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/booking")
public class BookingController {
    private  final BookingServices bookingServices;
    @PostMapping("/createBooking")
    public ResponseEntity<?> createBooking(@RequestBody @Valid BookingRequestDTO bookingRequestDTO) {
        ResponseEntity<?> booking = bookingServices.createBooking(bookingRequestDTO);
        return ResponseEntity.ok(booking);
    }

    @GetMapping("/getBookingById/{bookingId}")
    public ResponseEntity<?> getBookingById(@PathVariable UUID bookingId) {
        ResponseEntity<?> bookingById = bookingServices.getBookingById(bookingId);
        return ResponseEntity.ok(bookingById);
    }

    @PutMapping("/updateBookingById/{bookingId}")
    public ResponseEntity<?> updateBookingById(@PathVariable UUID bookingId, @RequestBody @Valid BookingRequestDTO bookingRequestDTO) {
        ResponseEntity<?> booking = bookingServices.updateBookingById(bookingId, bookingRequestDTO);
        return ResponseEntity.ok(booking);
    }

    @DeleteMapping("/deleteBookingById/{bookingId}")
    public ResponseEntity<?> deleteBookingById(@PathVariable UUID bookingId) {
        ResponseEntity<?> booking = bookingServices.deleteBookingById(bookingId);
        return ResponseEntity.ok(booking);
    }
    @GetMapping("/filter")
    public ResponseEntity<?> bookingFilter(@RequestParam(required = false) String shipperId,
                                        @RequestParam(required = false) String truckType,
                                        @RequestParam(required = false) String productType,
                                        @RequestParam(required = false) BookingStatus status,
                                           @RequestParam(required = false) String transporterId
    ) {
        ResponseEntity<?> bookingFilter = bookingServices.bookingFilter(shipperId,transporterId,status, truckType,productType);
        return ResponseEntity.ok(bookingFilter);
    }

    @GetMapping("/acceptBooking/{bookingId}")
    public ResponseEntity<?> acceptBooking(@PathVariable UUID bookingId) {
        ResponseEntity<?> booking = bookingServices.acceptBookingById(bookingId);
        return ResponseEntity.ok(booking);
    }


}
