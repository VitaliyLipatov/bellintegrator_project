package ru.bellintegrator.practice.controller;

import ru.bellintegrator.practice.service.OrganizationService;
import ru.bellintegrator.practice.view.OrganizationListFilter;
import ru.bellintegrator.practice.view.OrganizationToSave;
import ru.bellintegrator.practice.view.OrganizationView;

import java.util.List;

public class OrganizationServiceImpl implements OrganizationService {
    @Override
    public List<OrganizationView> list(OrganizationListFilter filter) {
        return null;
    }

    @Override
    public OrganizationView getById(Long id) {
        return null;
    }

    @Override
    public void update(OrganizationView view) {

    }

    @Override
    public void save(OrganizationToSave organizationToSave) {

    }

    @Override
    public void remove(Long id) {

    }
}
