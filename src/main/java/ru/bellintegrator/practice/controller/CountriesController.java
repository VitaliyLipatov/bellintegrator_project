package ru.bellintegrator.practice.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.bellintegrator.practice.service.CountriesService;
import ru.bellintegrator.practice.view.CountryView;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

/**
 * Класс контроллера для работы со справочником стран
 */
@RestController
@RequestMapping(value = "/api", produces = APPLICATION_JSON_VALUE)
public class CountriesController {

    private final CountriesService countriesService;

    /**
     * Конструктор
     *
     * @param countriesService сервис, предоставляющий методы для получения справочной информации о странах
     */
    @Autowired
    public CountriesController(CountriesService countriesService) {
        this.countriesService = countriesService;
    }

    /**
     * Возвращает список стран и их кодов
     *
     * @return список стран и их кодов
     */
    @ApiOperation(value = "Get coutries list", nickname = "getCountriesList", httpMethod = "POST")
    @PostMapping("/countries")
    public List<CountryView> list() {
        return countriesService.list();
    }
}
