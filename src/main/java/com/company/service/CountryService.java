package com.company.service;

import java.util.List;

import com.company.model.Country;
import com.company.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CountryService {

    private CountryRepository countryRepository;

    @Autowired
    public CountryService(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    public Iterable<Country> list() {
        return countryRepository.findAll();
    }

    public Country save(Country user) {
        return countryRepository.save(user);
    }

    public void save(List<Country> users) {
        countryRepository.saveAll(users);
    }
}
