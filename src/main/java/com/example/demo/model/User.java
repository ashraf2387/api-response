package com.example.demo.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;

@Data
@RequiredArgsConstructor
public class User implements Serializable {
    private final String name;
    private final String email;
    private final int age;
}
