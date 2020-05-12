package com.company;

import java.io.IOException;
import java.net.URL;

import com.company.model.Country;
import com.company.service.CountryService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = {"com.company.model","com.company.repository","com.company.service"})
public class Covid19countriesApplication {

    public static void main(String[] args) {
        SpringApplication.run(Covid19countriesApplication.class, args);

    }

//    @Bean
//    public static void saveCountryToDB(Country country) {
//
//        try {
//            URL url = new URL("https://api.covid19api.com/summary");
//            country = mapper.readValue(url, Country.class);
//            countryService.save(country);
//            System.out.println("Country Saved");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//    }

    @Bean
    CommandLineRunner runner(CountryService countryService) {
        return args -> {
            // read JSON and load json
            ObjectMapper mapper = new ObjectMapper();

            try {
                URL url = new URL("https://api.covid19api.com/summary");
                Country country = mapper.readValue(url, Country.class);
                countryService.save(country);
                System.out.println("Country Saved!");
            } catch (IOException e) {
                System.out.println("Unable to save users: " + e.getMessage());
            }
        };
    }

}
