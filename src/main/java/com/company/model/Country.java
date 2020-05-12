package com.company.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;


public class Country {

    @JsonProperty("Countries")
    private List<CountryData> listOfCountries;
    @JsonProperty("Global")
    private Global global;
    @JsonProperty("Date")
    private CurrentDate date;

    public Country() {

    }

    public Global getGlobal() {
        return global;
    }

    public void setGlobal(Global global) {
        this.global = global;
    }

    public CurrentDate getDate() {
        return date;
    }

    public void setDate(CurrentDate date) {
        this.date = date;
    }

    public List<CountryData> getListOfCountries() {
        return listOfCountries;
    }

    public void setListOfCountries(List<CountryData> listOfCountries) {
        this.listOfCountries = listOfCountries;
    }
}