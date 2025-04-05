package com.meetg.InternTask.Services;

import com.meetg.InternTask.DTO.LoadRequestDTO;
import com.meetg.InternTask.Entity.LoadStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

public interface LoadServices {
    ResponseEntity<?> createLoad(LoadRequestDTO loadRequestDTO);
    ResponseEntity<?> getAllLoadsFilter(String shipperId, String truckType,String productType, LoadStatus status);
    ResponseEntity<?> getLoadById(UUID loadId);
    ResponseEntity<?> updateLoadById(UUID loadId,LoadRequestDTO loadRequestDTO);
    ResponseEntity<?> deleteLoadById(UUID loadId);
    ResponseEntity<?> getAllLoad();
}
