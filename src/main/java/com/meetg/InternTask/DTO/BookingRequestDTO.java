package com.meetg.InternTask.DTO;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookingRequestDTO {
    private UUID id;
    @NotNull(message = "loadId cannot be blank")
    private UUID loadId;
    @NotBlank(message = "transporterId cannot be blank")
    private String transporterId;
    @Min(value = 0, message = "Price cannot be negative")
    private Double proposedRate;
    @NotBlank(message = "comment cannot be blank")
    private String comment;
}
