package com.meetg.InternTask.Services;

import com.meetg.InternTask.DTO.LoadRequestDTO;
import com.meetg.InternTask.DTO.LoadResponseDTO;
import com.meetg.InternTask.Entity.Load;
import com.meetg.InternTask.Entity.LoadStatus;
import com.meetg.InternTask.Exceptions.ResourceNotFoundException;
import com.meetg.InternTask.Repository.LoadRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import static com.meetg.InternTask.Entity.LoadStatus.POSTED;

@Service
@RequiredArgsConstructor
public class LoadServiceImpl implements LoadServices{
    private final LoadRepository loadRepository;
    private final ModelMapper modelMapper;
    @Transactional
    @Override
    public ResponseEntity<?> createLoad(LoadRequestDTO loadRequestDTO) {
        try {
            Load load = modelMapper.map(loadRequestDTO, Load.class);
            load.setStatus(POSTED);
            load.setDatePosted(Timestamp.valueOf(LocalDateTime.now()));
            Load saved = loadRepository.save(load);
            LoadResponseDTO loadResponseDTO = modelMapper.map(saved, LoadResponseDTO.class);
            return new ResponseEntity<>(loadResponseDTO, HttpStatus.CREATED);
        } catch (Exception e) {
            throw new RuntimeException(e.getLocalizedMessage());
        }
    }

    @Override
    public ResponseEntity<?> getAllLoadsFilter(String shipperId, String truckType, String productType, LoadStatus status) {
        try {
            List<Load> loadFilter = loadRepository.filterLoads(shipperId, truckType,productType,status).orElseThrow(()->new ResourceNotFoundException(HttpStatus.NOT_FOUND,"Load not found with given parameters"));
            if (loadFilter.isEmpty()){
                return new ResponseEntity<>("No booking found with given filter parameter", HttpStatus.NOT_FOUND);
            }
            List<LoadResponseDTO> loadResponseDTOList = loadFilter.stream().map(load -> modelMapper.map(load, LoadResponseDTO.class)).collect(Collectors.toList());
            return new ResponseEntity<>(loadResponseDTOList, HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            throw new RuntimeException(e.getLocalizedMessage());
        }
    }

    @Override
    public ResponseEntity<?> getLoadById(UUID loadId) {
        try {
            Load load = loadRepository.findById(loadId).orElseThrow(()-> new ResourceNotFoundException(HttpStatus.NOT_FOUND,"load not found with given Id : "+loadId));
            LoadResponseDTO loadResponseDTO = modelMapper.map(load, LoadResponseDTO.class);
            return new ResponseEntity<>(loadResponseDTO, HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            throw new RuntimeException(e.getLocalizedMessage());
        }
    }

    @Override
    public ResponseEntity<?> updateLoadById(UUID loadId, LoadRequestDTO loadRequestDTO) {
        try {
            Load load = loadRepository.findById(loadId).orElseThrow(()-> new ResourceNotFoundException(HttpStatus.NOT_FOUND,"load not found with given Id : "+loadId));
            loadRequestDTO.setId(loadId);
            modelMapper.map(loadRequestDTO, load);
            Load saved = loadRepository.save(load);
            LoadResponseDTO loadResponseDTO = modelMapper.map(saved, LoadResponseDTO.class);
            return new ResponseEntity<>(loadResponseDTO, HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            throw new RuntimeException(e.getLocalizedMessage());
        }
    }

    @Override
    public ResponseEntity<?> deleteLoadById(UUID loadId) {
        try {
            Load load = loadRepository.findById(loadId).orElseThrow(()-> new ResourceNotFoundException(HttpStatus.NOT_FOUND,"load not found with given Id : "+loadId));
            loadRepository.delete(load);
            return new ResponseEntity<>("delete record successfully",HttpStatus.NO_CONTENT);
        } catch (ResourceNotFoundException e) {
            throw new RuntimeException(e.getLocalizedMessage());
        }
    }

    @Override
    public ResponseEntity<?> getAllLoad() {
        try {
            List<Load> allLoads = loadRepository.findAll();
            List<LoadResponseDTO> loadResponseDTOList = allLoads.stream().map(load -> modelMapper.map(load, LoadResponseDTO.class)).collect(Collectors.toList());
            return new ResponseEntity<>(loadResponseDTOList, HttpStatus.OK);
        } catch (Exception e) {
            throw new RuntimeException(e.getLocalizedMessage());
        }
    }
}
