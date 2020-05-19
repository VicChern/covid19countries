package com.company;

import java.util.ArrayList;
import java.util.List;

import com.company.model.CountryData;
import com.company.service.CountryService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@SpringBootTest
class Covid19CountriesApplicationTest {

    @InjectMocks
    private Covid19CountriesApplication covid19countriesApplication;

    @Mock
    private static CountryService countryService;

    @Mock
    private static CountryData countryData;

    private static List<CountryData> listOfCountries;


    @BeforeAll
    static void setup() {
        MockitoAnnotations.initMocks(Covid19CountriesApplicationTest.class);

        listOfCountries = new ArrayList<>();
        listOfCountries.add(countryData);
    }

    @Test
    void addCountriesToTheDbTest() throws Exception {

        //given
        doNothing().when(countryService).save(anyList());
        covid19countriesApplication.addCountriesToTheDb(countryService);

        //when
        CommandLineRunner runner = covid19countriesApplication.addCountriesToTheDb(countryService);

        //then
        assertNotNull(runner);

    }

    @Test
    void getAffectedCountriesTest() throws Exception {

        //given
        when(countryService.listOfAllAffectedCountries()).thenReturn(listOfCountries);

        //when
        covid19countriesApplication.getAffectedCountries(countryService);

        //then
        assertEquals(1, listOfCountries.size());
        assertNotNull(countryService.listOfAllAffectedCountries());
        verify(countryService, times(1)).listOfAllAffectedCountries();

    }


    @Test
    void getPercentageOfRecoveredPersonsTest() throws Exception {

        //given
        CommandLineRunner runner = covid19countriesApplication.getPercentageOfRecoveredPersons();

        //when
        runner.run();

        //then
        assertNotNull(runner);

    }
}
