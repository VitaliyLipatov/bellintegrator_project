package ru.bellintegrator.practice.service;

import org.springframework.stereotype.Service;
import ru.bellintegrator.practice.view.CountryView;

import java.util.List;

@Service
public class CountriesServiceImpl implements CountriesService {
    @Override
    public List<CountryView> list() {
        return null;
    }
}
