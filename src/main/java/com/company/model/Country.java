package com.company.model;

import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;

@AllArgsConstructor
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Country country = (Country) o;
        return listOfCountries.equals(country.listOfCountries) &&
                global.equals(country.global) &&
                date.equals(country.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(listOfCountries, global, date);
    }
}