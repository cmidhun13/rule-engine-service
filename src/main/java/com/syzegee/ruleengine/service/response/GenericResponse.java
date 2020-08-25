package com.syzegee.ruleengine.service.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Riya Patel
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@NoArgsConstructor
public class GenericResponse<T> {
    private boolean success;
    private int status;
    private String message;
    private String description;
    private T data;
}
