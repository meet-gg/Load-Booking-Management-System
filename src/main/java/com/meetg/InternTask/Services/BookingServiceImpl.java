package com.meetg.InternTask.Services;

import com.meetg.InternTask.DTO.BookingRequestDTO;
import com.meetg.InternTask.DTO.BookingResponseDTO;
import com.meetg.InternTask.DTO.LoadRequestDTO;
import com.meetg.InternTask.Entity.Booking;
import com.meetg.InternTask.Entity.BookingStatus;
import com.meetg.InternTask.Entity.Load;
import com.meetg.InternTask.Entity.LoadStatus;
import com.meetg.InternTask.Exceptions.ResourceNotFoundException;
import com.meetg.InternTask.Repository.BookingRepository;
import com.meetg.InternTask.Repository.LoadRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static com.meetg.InternTask.Entity.BookingStatus.*;
import static com.meetg.InternTask.Entity.LoadStatus.*;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingServices {
    private final BookingRepository bookingRepository;
    private final LoadRepository loadRepository;
    private final ModelMapper modelMapper;
    @Override
    public ResponseEntity<?> createBooking(BookingRequestDTO bookingRequestDTO) {
        try {
            Load load = loadRepository.findById(bookingRequestDTO.getLoadId()).orElseThrow(() -> new ResourceNotFoundException(HttpStatus.NOT_FOUND, "Load not found with given load Id : " + bookingRequestDTO.getLoadId()));
            if (load.getStatus()==CANCELLED){
               return new ResponseEntity<>("Cannot booking the load because of load with given id is already "+ load.getStatus().name(),HttpStatus.CONFLICT);
            }
            load.setStatus(BOOKED);
            loadRepository.save(load);
            Booking booking = modelMapper.map(bookingRequestDTO, Booking.class);
            Booking saved = bookingRepository.save(booking);
            BookingResponseDTO bookingResponseDTO = modelMapper.map(saved, BookingResponseDTO.class);
            return new ResponseEntity<>(bookingResponseDTO, HttpStatus.CREATED);
        } catch (ResourceNotFoundException e) {
            throw new RuntimeException(e.getLocalizedMessage());
        }
    }

    @Override
    public ResponseEntity<?> bookingFilter(String shipperId, String transporterId, BookingStatus status, String truckType,String productType) {
        try {
            List<Booking> bookings = bookingRepository.bookingFilter(shipperId, transporterId, status, truckType,productType);
            if (bookings.isEmpty()){
                return new ResponseEntity<>("No booking found with given filter parameter", HttpStatus.NOT_FOUND);
            }
            List<BookingResponseDTO> bookingResponseDTOS = bookings.stream().map(booking -> modelMapper.map(booking, BookingResponseDTO.class)).collect(Collectors.toList());
            return new ResponseEntity<>(bookingResponseDTOS, HttpStatus.OK);
        } catch (Exception e) {
            throw new RuntimeException(e.getLocalizedMessage());
        }
    }


    @Override
    public ResponseEntity<?> getBookingById(UUID bookingId) {
        try {
            Booking booking = bookingRepository.findById(bookingId).orElseThrow(() -> new ResourceNotFoundException(HttpStatus.NOT_FOUND, "Booking not found with given id : " + bookingId));
            BookingResponseDTO bookingResponseDTO = modelMapper.map(booking, BookingResponseDTO.class);
            return new ResponseEntity<>(bookingResponseDTO, HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            throw new RuntimeException(e.getLocalizedMessage());
        }

    }

    @Override
    public ResponseEntity<?> updateBookingById(UUID bookingId, BookingRequestDTO bookingRequestDTO) {
        try {
            Booking booking = bookingRepository.findById(bookingId).orElseThrow(() -> new ResourceNotFoundException(HttpStatus.NOT_FOUND, "Booking not found with given id : " + bookingId));
            bookingRequestDTO.setId(bookingId);
            modelMapper.map(bookingRequestDTO,booking);
            Booking saved = bookingRepository.save(booking);
            BookingResponseDTO bookingResponseDTO = modelMapper.map(saved, BookingResponseDTO.class);
            return new ResponseEntity<>(bookingResponseDTO, HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            throw new RuntimeException(e.getLocalizedMessage());
        }
    }

    @Override
    public ResponseEntity<?> deleteBookingById(UUID bookingId) {
        try {
            Booking booking = bookingRepository.findById(bookingId).orElseThrow(() -> new ResourceNotFoundException(HttpStatus.NOT_FOUND, "Booking not found with given id : " + bookingId));
            Load load = loadRepository.findById(booking.getLoad().getId()).orElseThrow(() -> new ResourceNotFoundException(HttpStatus.NOT_FOUND, "Load not found with given id : " + booking.getLoad().getId()));
            load.setStatus(CANCELLED);
            loadRepository.save(load);
            bookingRepository.delete(booking);
            return new ResponseEntity<>("delete Booking successfully",HttpStatus.NO_CONTENT);
        } catch (ResourceNotFoundException e) {
            throw new RuntimeException(e.getLocalizedMessage());
        }
    }

    @Override
    public ResponseEntity<?> acceptBookingById(UUID bookingId) {
        try {
            Booking booking = bookingRepository.findById(bookingId).orElseThrow(() -> new ResourceNotFoundException(HttpStatus.NOT_FOUND, "Booking not found with given id : " + bookingId));
            booking.setStatus(ACCEPTED);
            Booking saved = bookingRepository.save(booking);
            BookingResponseDTO bookingResponseDTO = modelMapper.map(saved, BookingResponseDTO.class);
            return new ResponseEntity<>(bookingResponseDTO, HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            throw new RuntimeException(e.getLocalizedMessage());
        }
    }

    @Override
    public ResponseEntity<?> getBookingByLoadId(UUID loadId) {
        try {
            List<Booking> bookings = bookingRepository.findByLoadId(loadId).orElseThrow(() -> new ResourceNotFoundException(HttpStatus.NOT_FOUND, "Booking not found with id : " + loadId));
            if (bookings.isEmpty()){
                return new ResponseEntity<>("Booking not Found with given Load Id : "+loadId,HttpStatus.NOT_FOUND);
            }
            List<BookingResponseDTO> bookingResponseDTOS = bookings.stream().map(booking -> modelMapper.map(booking, BookingResponseDTO.class)).collect(Collectors.toList());
            return new ResponseEntity<>(bookingResponseDTOS, HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            throw new RuntimeException(e.getLocalizedMessage());
        }
    }
}
