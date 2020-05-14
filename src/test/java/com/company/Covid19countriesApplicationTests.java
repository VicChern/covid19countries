package com.company;

import java.util.ArrayList;
import java.util.List;

import com.company.model.Country;
import com.company.model.CountryData;
import com.company.model.CurrentDate;
import com.company.model.Global;
import com.company.service.CountryService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;


@SpringBootTest
class Covid19countriesApplicationTests {

    @InjectMocks
    private Covid19countriesApplication covid19countriesApplication;

    @Mock
    private static CountryService countryService;

    private static List<CountryData> listOfCountries;
    private static Global globalData;

    @BeforeAll
    static void setup() {
        MockitoAnnotations.initMocks(Covid19countriesApplicationTests.class);

        CountryData countryData = new CountryData();
        countryData.setId(1L);
        countryData.setCountry("Albania");
        countryData.setCountryCode("AL");
        countryData.setSlug("albania");
        countryData.setNewConfirmed(29);
        countryData.setTotalConfirmed(333);
        countryData.setNewDeaths(3);
        countryData.setTotalDeaths(20);
        countryData.setNewRecovered(10);
        countryData.setTotalRecovered(99);
        countryData.setDate("2020-04-05T06:37:00Z");

        CurrentDate date = new CurrentDate();
        date.setDate("2020-04-05T06:37:00Z");

        listOfCountries = new ArrayList<>();
        listOfCountries.add(countryData);


        globalData = new Global();
        globalData.setNewConfirmed(100282);
        globalData.setTotalConfirmed(1162857);
        globalData.setNewDeaths(5658);
        globalData.setTotalDeaths(63263);
        globalData.setNewRecovered(15405);
        globalData.setTotalRecovered(230845);


        Country country = new Country();
        country.setListOfCountries(listOfCountries);
        country.setDate(date);
        country.setGlobal(globalData);

    }

    @Test
    void addCountriesToTheDbTest() throws Exception {
       assertNotNull(covid19countriesApplication.addCountriesToTheDb(countryService));
       doNothing().when(countryService).save(listOfCountries);
    }

    @Test
    void getAffectedCountriesTest() throws Exception {
        when(countryService.listOfAllAffectedCountries()).thenReturn(listOfCountries);

        covid19countriesApplication.getAffectedCountries(countryService);

        assertEquals(1, listOfCountries.size());
        assertNotNull(countryService.listOfAllAffectedCountries());


    }

    @Test
    void getPercentageOfRecoveredPersonsTest() throws Exception {
        int percentageOfRecoveredPersons = globalData.getTotalRecovered() / (globalData.getTotalConfirmed() / 100);

        assertNotNull(covid19countriesApplication.getPercentageOfRecoveredPersons());
        assertEquals(19, percentageOfRecoveredPersons);


    }


}
