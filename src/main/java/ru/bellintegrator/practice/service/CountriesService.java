package ru.bellintegrator.practice.service;

import ru.bellintegrator.practice.view.CountryView;

import java.util.List;

/**
 * Интерфейс, предоставляющий методы для работы со справочником стран
 */
public interface CountriesService {

    /**
     * Возвращает список стран и их кодов
     *
     * @return список стран и их кодов
     */
    List<CountryView> list();
}
