package com.company.repository;

import com.company.model.CountryData;
import org.springframework.data.repository.CrudRepository;

public interface CountryRepository extends CrudRepository<CountryData, Long> {



}
