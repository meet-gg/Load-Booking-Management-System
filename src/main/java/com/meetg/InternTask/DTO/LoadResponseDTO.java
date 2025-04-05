package com.meetg.InternTask.DTO;

import lombok.*;

import java.sql.Timestamp;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoadResponseDTO {
    private UUID id;
    private String shipperId;
    private FacilityDTO facility;
    private String productType;
    private String truckType;
    private Integer noOfTrucks;
    private Double weight;
    private String comment;
    private Timestamp datePosted;
    private String status;
}
