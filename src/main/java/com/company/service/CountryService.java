package com.company.service;

import java.util.ArrayList;
import java.util.List;

import com.company.model.CountryData;
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

    public Iterable<CountryData> listOfAllAffectedCountries() {
        List <CountryData> listOfAffectedCountries = new ArrayList<>();
        List <CountryData> listOfAllCountries  =(ArrayList<CountryData>) countryRepository.findAll();
        for(CountryData countryData:listOfAllCountries){
            if (countryData.getTotalConfirmed()  > 0){
                listOfAffectedCountries.add(countryData);
            }
        }
        return listOfAffectedCountries;
    }

    public CountryData save(CountryData countryData) {
        return countryRepository.save(countryData);
    }

    public void save(List<CountryData> countryMetadata) {
        countryRepository.saveAll(countryMetadata);
    }
}
