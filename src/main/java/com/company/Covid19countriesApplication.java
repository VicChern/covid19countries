package com.company;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import com.company.model.Country;
import com.company.model.CountryData;
import com.company.service.CountryService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Covid19countriesApplication {
    private final static Logger logger = LoggerFactory.getLogger(Covid19countriesApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(Covid19countriesApplication.class, args);

    }

    @Bean
    CommandLineRunner saveDataToTheDb(CountryService countryService) {
        return args -> {

            // Jackson mapper for reading JSON and loading json
            ObjectMapper mapper = new ObjectMapper();

            try {
                logger.debug("Try to get Countries list and store to the Country object");
                URL url = new URL("https://api.covid19api.com/summary");
                Country country = mapper.readValue(url, Country.class);

                logger.debug("Saving countries to the DB");
                countryService.save(country.getListOfCountries());
                logger.info("Countries were save");
            } catch (IOException e) {
                System.out.println("Unable to save Countries: " + e.getMessage());
            }
        };
    }

    @Bean
    CommandLineRunner getAffectedCountries(CountryService countryService) {
        return args -> {
            try {
                List<CountryData> listOFAffectedCountries = (ArrayList<CountryData>)countryService.listOfAllAffectedCountries();
                System.out.println("Count of all affected countries is:" + listOFAffectedCountries.size());

            } catch (NullPointerException e) {
                System.out.println("There is no affected countries");
            }
        };
    }

}
