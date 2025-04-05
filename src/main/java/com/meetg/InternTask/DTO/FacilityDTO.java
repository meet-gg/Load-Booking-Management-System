package com.meetg.InternTask.DTO;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FacilityDTO {
    @NotBlank(message = "loadingPoint cannot be blank")
    private String loadingPoint;
    @NotBlank(message = "unloadingPoint cannot be blank")
    private String unloadingPoint;
    @FutureOrPresent(message = "Loading date must be in the present or future")
    private Timestamp loadingDate;
    @Future(message = "Unloading date must be in the future")
    private Timestamp unloadingDate;
}
