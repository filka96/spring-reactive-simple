package com.example.reactive.web.rest.util;

import lombok.experimental.UtilityClass;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * Utility class for ResponseEntity creation.
 */
@UtilityClass
public class ResponseUtil {

    /**
     * A shortcut for creating a {@link ResponseEntity} with {@link HttpStatus#NOT_FOUND} status
     *
     * @return the created {@link ResponseEntity} with {@link HttpStatus#NOT_FOUND} status
     */
    public static <T> ResponseEntity<T> notFound() {
        return ResponseEntity.notFound().build();
    }

}
