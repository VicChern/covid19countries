package com.company.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor

public class Country {

    @JsonProperty("Global")
    @JsonIgnore
    Global global;

    @JsonProperty("Date")
    @JsonIgnore
    CurrentDate date;

    @JsonProperty("Countries")
    List<CountryMetadata> countries = new ArrayList<>();

    public Country() {

    }
}