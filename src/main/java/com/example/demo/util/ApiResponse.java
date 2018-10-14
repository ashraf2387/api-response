package com.example.demo.util;

import net.sf.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static java.util.Objects.isNull;

public class ApiResponse implements ApiMeta, ApiDataMap {

    private Object data;

    private Map<String, Object> meta;

    private Map<String, Object> dataMap;

    private ApiError apiError;

    private ResponseType responseType;

    private ApiResponse(ResponseType responseType) {
        this.responseType = responseType;
    }

    public static ApiMeta success(Object data) {
        ApiResponse response = new ApiResponse(ResponseType.DATA);
        response.data = data;

        return response;
    }

    public static ApiDataMap success() {
        return new ApiResponse(ResponseType.DATA_MAP);
    }

    public static ApiMeta error(ApiError error) {
        ApiResponse response = new ApiResponse(ResponseType.ERROR);
        response.apiError = error;

        return response;
    }

    @Override
    public ApiResponse addDataMapItem(String name, Object data) {
        if (Objects.isNull(dataMap)) {
            dataMap = new HashMap<>();
        }

        dataMap.put(name, data);
        return this;
    }

    @Override
    public ApiResponse addMetaItem(String name, Object data) {
        if (isNull(this.meta)) {
            meta = new HashMap<>();
        }

        meta.put(name, data);
        return this;
    }

    @Override
    public JSONObject getJsonResponse() {

        JSONObject jsonObject = new JSONObject();
        switch (this.responseType) {
            case DATA:
                jsonObject.put("data", data);
                break;
            case DATA_MAP:
                jsonObject.put("data", dataMap);
                break;
            case ERROR:
                jsonObject.put("errors", apiError);
        }

        jsonObject.put("meta", meta);

        return jsonObject;
    }
}
