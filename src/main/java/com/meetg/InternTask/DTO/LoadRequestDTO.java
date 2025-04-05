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
public class LoadRequestDTO {
    private UUID id;
    @NotBlank(message = "shipperId cannot be blank")
    private String shipperId;
    private FacilityDTO facility;
    @NotBlank(message = "productType cannot be blank")
    private String productType;
    @NotBlank(message = "truckType cannot be blank")
    private String truckType;
    @NotNull(message = "noOfTrucks cannot be blank")
    private Integer noOfTrucks;
    @Min(value = 0, message = "weight cannot be negative")
    private Double weight;
    @NotBlank(message = "comment cannot be blank")
    private String comment;
}
