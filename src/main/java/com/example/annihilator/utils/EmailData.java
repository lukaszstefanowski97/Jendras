package com.example.annihilator.utils;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.Min;

@AllArgsConstructor
@Data
public class EmailData {

    @Min(value = 2)
    private String subject;

    @Min(value = 2)
    private String content;
}
