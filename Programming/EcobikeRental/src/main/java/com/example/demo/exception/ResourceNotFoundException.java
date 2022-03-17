package com.example.demo.exception;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

/**
 * class dùng để bắt lỗi khi không tìm thấy thông tin
 * @author nguyễn duy hoài lâm
 */
@ResponseStatus (value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {
    private final String resourceName;
    private final String fieldName;
    private final Object fieldValue;

    public ResourceNotFoundException (String resourceName, String fieldName, Object fieldValue) {
        super(String.format("%s not found with %s : '%s'", resourceName, fieldName, fieldValue));
        this.resourceName = resourceName;
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }

    public String getResourceName () {
        return resourceName;
    }

    public String getFieldName () {
        return fieldName;
    }

    public Object getFieldValue () {
        return fieldValue;
    }
}