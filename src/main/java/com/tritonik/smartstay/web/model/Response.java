package com.tritonik.smartstay.web.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Response<T> {

    /**
     * HTTP status
     */
    private Integer status; // Status code (200, 400, 404)

    /**
     * Return Data
     */
    private T data;
    /**
     * Map for errors
     */
    private Map<String, List<String>> errors;
}
