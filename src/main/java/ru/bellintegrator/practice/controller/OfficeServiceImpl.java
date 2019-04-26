package ru.bellintegrator.practice.controller;

import org.springframework.stereotype.Service;
import ru.bellintegrator.practice.service.OfficeService;
import ru.bellintegrator.practice.view.OfficeListFilter;
import ru.bellintegrator.practice.view.OfficeToSave;
import ru.bellintegrator.practice.view.OfficeToUpdate;

import java.util.List;

@Service
public class OfficeServiceImpl implements OfficeService {


    @Override
    public OfficeToUpdate getById(Long id) {
        return null;
    }

    @Override
    public List<OfficeToUpdate> list(OfficeListFilter filter) {
        return null;
    }

    @Override
    public void update(OfficeToUpdate view) {

    }

    @Override
    public void save(OfficeToSave officeToSave) {

    }
}
