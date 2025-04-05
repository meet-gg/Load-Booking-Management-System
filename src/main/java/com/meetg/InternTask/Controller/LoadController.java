package com.meetg.InternTask.Controller;

import com.meetg.InternTask.DTO.LoadRequestDTO;
import com.meetg.InternTask.Entity.LoadStatus;
import com.meetg.InternTask.Services.BookingServices;
import com.meetg.InternTask.Services.LoadServices;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/load")
public class LoadController {
    private final LoadServices loadServices;
    private final BookingServices bookingServices;

    @PostMapping("/createLoad")
    public ResponseEntity<?> createLoad(@RequestBody @Valid LoadRequestDTO loadRequestDTO) {
        ResponseEntity<?> load = loadServices.createLoad(loadRequestDTO);
        return ResponseEntity.ok(load);
    }

    @GetMapping("/getLoadById/{loadId}")
    public ResponseEntity<?> getLoadById(@PathVariable UUID loadId) {
        ResponseEntity<?> loadById = loadServices.getLoadById(loadId);
        return ResponseEntity.ok(loadById);
    }

    @GetMapping("/filter/")
    public ResponseEntity<?> filterLoad(@RequestParam(required = false) String shipperId,
                                        @RequestParam(required = false) String truckType,
                                        @RequestParam(required = false) String productType,
                                        @RequestParam(required = false) LoadStatus status
                                        ) {
        ResponseEntity<?> allLoadsByShipperIdAndTruckType = loadServices.getAllLoadsFilter(shipperId, truckType, productType, status);
        return ResponseEntity.ok(allLoadsByShipperIdAndTruckType);
    }

    @PutMapping("/updateLoadById/{loadId}")
    public ResponseEntity<?> updateLoadById(@PathVariable UUID loadId, @RequestBody @Valid LoadRequestDTO loadRequestDTO) {
        ResponseEntity<?> load = loadServices.updateLoadById(loadId, loadRequestDTO);
        return ResponseEntity.ok(load);
    }

    @DeleteMapping("/deleteLoadById/{loadId}")
    public ResponseEntity<?> deleteLoadById(@PathVariable UUID loadId) {
        ResponseEntity<?> load = loadServices.deleteLoadById(loadId);
        return ResponseEntity.ok(load);
    }

    @GetMapping("/getAllLoad")
    public ResponseEntity<?> getAllLoad() {
        ResponseEntity<?> loads=loadServices.getAllLoad();
        return ResponseEntity.ok(loads);
    }

    @GetMapping("/getBookingByLoadId/{loadId}")
    public ResponseEntity<?> getBookingByLoadId(@PathVariable UUID loadId) {
        ResponseEntity<?> bookings= bookingServices.getBookingByLoadId(loadId);
        return ResponseEntity.ok(bookings);
    }
}
