package com.meetg.InternTask.Advices;

import lombok.*;
import org.springframework.http.HttpStatus;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ApiError {
    private HttpStatus httpStatus;
    private String message;
    private List<String> otherError;

}
