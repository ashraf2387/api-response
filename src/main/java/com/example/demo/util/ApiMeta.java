package com.example.demo.util;

public interface ApiMeta extends ApiJsonResponse {
    ApiMeta addMetaItem(String name, Object value);
}
