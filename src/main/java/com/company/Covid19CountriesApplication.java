package com.company;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import com.company.model.Country;
import com.company.model.CountryData;
import com.company.model.Global;
import com.company.service.CountryService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Covid19CountriesApplication {
    private final static Logger logger = LoggerFactory.getLogger(Covid19CountriesApplication.class);
    private Country country;

    public static void main(String[] args) {
        SpringApplication.run(Covid19CountriesApplication.class, args);

    }

    @Bean
    CommandLineRunner addCountriesToTheDb(CountryService countryService) {
        logger.info("Entered addCountriesToTheDb method");
        return args -> {

            // Jackson mapper for reading JSON and loading json
            ObjectMapper mapper = new ObjectMapper();

            try {
                logger.debug("Try to get Countries list and store to the Country object");
                URL url = new URL("https://api.covid19api.com/summary");
                country = mapper.readValue(url, Country.class);

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
        logger.info("Entered to getAffectedCountries method");
        return args -> {
            try {
                List<CountryData> listOfAffectedCountries = (ArrayList<CountryData>) countryService.listOfAllAffectedCountries();
                System.out.println("Count of all affected countries is:" + listOfAffectedCountries.size());

            } catch (NullPointerException e) {
                System.out.println("There is no affected countries" + e.getMessage());
            }

        };
    }

    @Bean
    CommandLineRunner getPercentageOfRecoveredPersons() {
        logger.info("Entered to getPercentageOfRecoveredPersons method");
        return args -> {
            try {
                logger.debug("Try to get global Data and store to the Global object");
                Global globalData = country.getGlobal();

                int percentageOfRecoveredPersons = globalData.getTotalRecovered() / (globalData.getTotalConfirmed() / 100);

                System.out.println("Global recovery rate is " + percentageOfRecoveredPersons + " %");
            } catch (NullPointerException e) {
                System.out.println("There is no affected countries" + e.getMessage());
            }
        };
    }

}
