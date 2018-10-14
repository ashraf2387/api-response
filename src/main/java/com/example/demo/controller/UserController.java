package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.util.ApiError;
import com.example.demo.util.ApiJsonResponse;
import com.example.demo.util.ApiResponse;
import lombok.RequiredArgsConstructor;
import net.sf.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(path = "users")
@RequiredArgsConstructor
public class UserController {

    @GetMapping(value = "/data", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<JSONObject> testData() {
        User user = new User("hasan", "hasan@gmail.com", 21);
        ApiJsonResponse response = ApiResponse.success(user)
                .addMetaItem("version", "1.0");

        return new ResponseEntity<>(response.getJsonResponse(), HttpStatus.OK);
    }

    @GetMapping(value = "/data-map", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<JSONObject> testDataMap() {
        ApiJsonResponse response = ApiResponse.success()
                .addDataMapItem("name", "hasan")
                .addDataMapItem("email", "hasan@gmail.com")
                .addDataMapItem("age", "21")
                .addMetaItem("version", "1.0");

        return new ResponseEntity<>(response.getJsonResponse(), HttpStatus.OK);
    }

    @GetMapping(value = "/data-error", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<JSONObject> testError() {
        ApiError error = new ApiError("100", "Insufficient Privilege");
        error.setGlobalErrors(Collections.singletonList("Global Error"));
        error.setFieldErrors(Collections.singletonMap("field", Collections.singletonList("Field Error")));

        ApiJsonResponse response = ApiResponse.error(error)
                .addMetaItem("version", "1.0");

        return new ResponseEntity<>(response.getJsonResponse(), HttpStatus.BAD_REQUEST);
    }
}
