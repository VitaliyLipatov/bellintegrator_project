package ru.bellintegrator.practice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bellintegrator.practice.dao.CountriesDao;
import ru.bellintegrator.practice.model.Country;
import ru.bellintegrator.practice.view.CountryView;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Сервис, предоставляющий методы для получения справочной информации о странах
 */
@Service
public class CountriesServiceImpl implements CountriesService {

    private final CountriesDao countriesDao;

    /**
     * Конструктор
     *
     * @param countriesDao DAO
     */
    @Autowired
    public CountriesServiceImpl(CountriesDao countriesDao) {
        this.countriesDao = countriesDao;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = true)
    public List<CountryView> list() {
        List<Country> list = countriesDao.list();
        return list.stream().map(mapCountry()).collect(Collectors.toList());
    }

    private Function<Country, CountryView> mapCountry() {
        return c -> {
            CountryView view = new CountryView();
            view.code = c.getCode();
            view.name = c.getName();
            return view;
        };
    }
}
