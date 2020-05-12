package com.company;

import com.company.model.CountryData;
import com.company.service.CountryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/countries")
public class CountryController {
    private CountryService userService;

    public CountryController(CountryService userService) {
        this.userService = userService;
    }

    @GetMapping("/list")
    public Iterable<CountryData> list() {
        return userService.listOfAllAffectedCountries();
    }
}
