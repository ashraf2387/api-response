package com.example.demo.util;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Data
@RequiredArgsConstructor
public class ApiError implements Serializable {

    private static final long serialVersionUID = 1L;

    private final String code;

    private final String message;

    private List<String> globalErrors;

    private Map<String, List<String>> fieldErrors;
}
