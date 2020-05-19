package com.company;

import java.util.ArrayList;
import java.util.List;

import com.company.model.CountryData;
import com.company.repository.CountryRepository;
import com.company.service.CountryService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class CountryServiceTest {

    @InjectMocks
    private CountryService countryService;

    @Spy
    public CountryRepository countryRepository;

    @Mock
    public CountryData countryData;

    private List<CountryData> countryDataList;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        countryDataList = new ArrayList<>();
        countryDataList.add(countryData);
    }

    @Test
    public void listOfAllAffectedCountriesTest() throws Exception {

        //given
        when(countryRepository.findAll()).thenReturn(countryDataList);

        //when
        countryService.listOfAllAffectedCountries();

        //than
        assertNotNull(countryDataList);
        assertEquals(1, countryDataList.size());
    }

    @Test
    public void saveTest() throws Exception {

        //given
        when(countryRepository.saveAll(anyList())).thenReturn(anyList());

        //when
        countryService.save(countryDataList);

        //than
        assertNotNull(countryRepository.saveAll(anyList()));
        verify(countryRepository, times(2)).saveAll(anyList());

    }

}