package com.meetg.InternTask.DTO;

import lombok.*;

import java.sql.Timestamp;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookingResponseDTO {
    private UUID id;
    private UUID loadId;
    private String transporterId;
    private Double proposedRate;
    private String comment;
    private String status;
    private Timestamp requestedAt;
}
