package com.company.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CurrentDate {

    @JsonProperty("Date")
    private String date;

    public CurrentDate() {
    }
}
