package com.example.exception;

import lombok.Data;
import lombok.experimental.Builder;

/**
 * @author prokade
 */
@Builder
@Data
public class ApiError {

    String errorMessage;
}
