package ru.bellintegrator.practice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import ru.bellintegrator.practice.dao.OrganizationDao;
import ru.bellintegrator.practice.exception.RecordNotFoundException;
import ru.bellintegrator.practice.exception.WrongRequestException;
import ru.bellintegrator.practice.model.Organization;
import ru.bellintegrator.practice.view.OrganizationListFilter;
import ru.bellintegrator.practice.view.OrganizationToSave;
import ru.bellintegrator.practice.view.OrganizationView;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Сервис, предоставляющий методы для работы с организациями
 */
@Service
public class OrganizationServiceImpl implements OrganizationService {

    private final OrganizationDao organizationDao;

    /**
     * Конструктор
     *
     * @param organizationDao DAO организаций
     */
    @Autowired
    public OrganizationServiceImpl(OrganizationDao organizationDao) {
        this.organizationDao = organizationDao;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = true)
    public List<OrganizationView> list(OrganizationListFilter filterView) {
        if (filterView == null) {
            throw new WrongRequestException("Empty input data ");
        }
        validateFilter(filterView);
        Organization filter = new Organization();
        filter.setName(filterView.name);
        filter.setInn(filterView.inn);
        filter.setActive(filterView.isActive);
        List<Organization> list = organizationDao.list(filter);
        return list.stream().map(mapOrganization()).collect(Collectors.toList());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = true)
    public OrganizationView getById(Long id) {
        if (id == null) {
            throw new WrongRequestException("Field \" id \" is null.");
        }
        Organization organization = organizationDao.getById(id);
        if (organization == null) {
            throw new RecordNotFoundException("Record with id = " + id + " was not found in Organization.");
        }
        OrganizationView organizationView = new OrganizationView();
        organizationView.id = organization.getId();
        organizationView.name = organization.getName();
        organizationView.fullName = organization.getFullName();
        organizationView.inn = organization.getInn();
        organizationView.kpp = organization.getKpp();
        organizationView.address = organization.getAddress();
        organizationView.phone = organization.getPhone();
        organizationView.isActive = organization.getActive();
        return organizationView;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = true)
    public void update(OrganizationView updateView) {
        if (updateView == null) {
            throw new WrongRequestException("Empty input data ");
        }
        validateUpdate(updateView);
        Organization updateOrganization = new Organization();
        updateOrganization.setName(updateView.name);
        updateOrganization.setFullName(updateView.fullName);
        updateOrganization.setInn(updateView.inn);
        updateOrganization.setKpp(updateView.kpp);
        updateOrganization.setAddress(updateView.address);
        if (updateView.phone != null) {
            updateOrganization.setPhone(StringUtils.isEmpty(updateView.phone) ? null : updateView.phone);
        } else {
            updateOrganization.setPhone(organizationDao.getById(updateView.id).getPhone());
        }
        updateOrganization.setActive(updateView.isActive != null ? updateView.isActive : organizationDao.getById(updateView.id).getActive());
        organizationDao.update(updateView.id, updateOrganization);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void save(OrganizationToSave saveView) {
        if (saveView == null) {
            throw new WrongRequestException("Empty input data ");
        }
        validateSaveView(saveView);
        Organization saveOrganization = new Organization(saveView.name, saveView.fullName, saveView.inn,
                saveView.kpp, saveView.address, saveView.phone, saveView.isActive);
        organizationDao.save(saveOrganization);
    }

    private void validateFilter(OrganizationListFilter filterView) {
        if (filterView.name == null) {
            throw new WrongRequestException("Field \"name\" is null.");
        }
    }

    private Function<Organization, OrganizationView> mapOrganization() {
        return o -> {
            OrganizationView view = new OrganizationView();
            view.id = o.getId();
            view.name = o.getName();
            view.fullName = o.getFullName();
            view.inn = o.getInn();
            view.kpp = o.getKpp();
            view.address = o.getAddress();
            view.phone = o.getPhone();
            view.isActive = o.getActive();
            return view;
        };
    }

    private void validateUpdate(OrganizationView updateView) {
        StringBuilder message = new StringBuilder();
        if (updateView.id == null) {
            message.append("Field \"id\"is null");
        }
        if (updateView.name == null || !isNameValid(updateView.name)) {
            message.append("Field \"name\"is null or invalid");
        }
        if (updateView.fullName == null || !isFullNameValid(updateView.fullName)) {
            message.append("Field \"fullName\"is null or invalid");
        }
        if (updateView.inn == null || !isInnValid(updateView.inn)) {
            message.append("Field \"inn\"is null or invalid");
        }
        if (updateView.kpp == null || !isKppValid(updateView.kpp)) {
            message.append("Field \"kpp\"is null or invalid");
        }
        if (updateView.address == null || !isAddressValid(updateView.address)) {
            message.append("Field \"address\"is null or invalid");
        }
        if (updateView.phone != null && !isPhoneValid(updateView.phone)) {
            message.append("Field \"phone\"is null or invalid");
        }
        if (message.length() > 0) {
            throw new WrongRequestException(message.toString().trim());
        }
        if (organizationDao.getById(updateView.id) == null) {
            throw new RecordNotFoundException("Record with id " + updateView.id + "wasn't found");
        }
    }

    private boolean isNameValid(String name) {
        return name.matches("[a-zA-Zа-яА-Я\"\\s-]{1,50}");
    }

    private boolean isFullNameValid(String fullName) {
        return fullName.matches("[a-zA-Zа-яА-Я\"\\s,.-]{1,100}");
    }

    private boolean isInnValid(String inn) {
        return inn.matches("[0-9]{10}");
    }

    private boolean isKppValid(String kpp) {
        return kpp.matches("[0-9]{9}");
    }

    private boolean isAddressValid(String address) {
        return address.matches("[a-zA-Zа-яА-Я0-9\"\\s,.-]{1,200}");
    }

    private boolean isPhoneValid(String phone) {
        return phone.matches("[0-9]{20}");
    }

    private void validateSaveView(OrganizationToSave saveView) {
        StringBuilder message = new StringBuilder();
        if (saveView.name == null || !isNameValid(saveView.name)) {
            message.append("Field \"name\"is null or invalid");
        }
        if (saveView.fullName == null || !isFullNameValid(saveView.fullName)) {
            message.append("Field \"fullName\"is null or invalid");
        }
        if (saveView.inn == null || !isInnValid(saveView.inn)) {
            message.append("Field \"inn\"is null or invalid");
        }
        if (saveView.kpp == null || !isKppValid(saveView.kpp)) {
            message.append("Field \"kpp\"is null or invalid");
        }
        if (saveView.address == null || !isAddressValid(saveView.address)) {
            message.append("Field \"address\"is null or invalid");
        }
        if (saveView.phone != null && !isPhoneValid(saveView.phone)) {
            message.append("Field \"phone\"is invalid");
        }
        if (saveView.isActive == null) {
            message.append("Field \"isActive\"is null");
        }
        if (message.length() > 0) {
            throw new WrongRequestException(message.toString().trim());
        }
    }
}
