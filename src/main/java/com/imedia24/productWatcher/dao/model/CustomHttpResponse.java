package com.imedia24.productWatcher.dao.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CustomHttpResponse {
    private boolean success;
    private String message;
    private Object data;
}
