package ru.bellintegrator.practice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import ru.bellintegrator.practice.dao.OfficeDao;
import ru.bellintegrator.practice.dao.OrganizationDao;
import ru.bellintegrator.practice.exception.RecordNotFoundException;
import ru.bellintegrator.practice.exception.WrongRequestException;
import ru.bellintegrator.practice.model.Office;
import ru.bellintegrator.practice.view.OfficeListFilter;
import ru.bellintegrator.practice.view.OfficeToSave;
import ru.bellintegrator.practice.view.OfficeToUpdate;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Сервис, предоставляющий методы для работы с офисами
 */
@Service
public class OfficeServiceImpl implements OfficeService {

    private final OfficeDao officeDao;
    private final OrganizationDao organizationDao;

    /**
     * Конструктор
     *
     * @param officeDao       DAO-слой для работы с офисами
     * @param organizationDao DAO-слой для работы с организациями
     */
    @Autowired
    public OfficeServiceImpl(OfficeDao officeDao, OrganizationDao organizationDao) {
        this.officeDao = officeDao;
        this.organizationDao = organizationDao;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = true)
    public List<OfficeToUpdate> list(OfficeListFilter filterView) {
        validateFilter(filterView);
        Office filter = new Office();
        filter.setOrganization(organizationDao.getById(filterView.orgId));
        filter.setName(filterView.name);
        filter.setPhone(filterView.phone);
        filter.setActive(filterView.isActive);
        List<Office> list = officeDao.list(filter);
        return list.stream().map(mapOffice()).collect(Collectors.toList());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = true)
    public OfficeToUpdate getById(Long officeId) {
        if (officeId == null) {
            throw new WrongRequestException("Field \"id\" is null");
        }
        Office office = officeDao.getById(officeId);
        if (office == null) {
            throw new RecordNotFoundException("Record with id " + officeId + "was not found");
        }
        OfficeToUpdate updateView = new OfficeToUpdate();
        updateView.id = office.getId();
        updateView.name = office.getName();
        updateView.address = office.getAddress();
        updateView.phone = office.getPhone();
        updateView.isActive = office.getActive();
        return updateView;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void update(OfficeToUpdate updateView) {
        validateUpdate(updateView);
        Office updateOffice = new Office();
        updateOffice.setName(updateView.name);
        updateOffice.setAddress(updateView.address);
        if (updateView.phone != null) {
            updateOffice.setPhone(StringUtils.isEmpty(updateView.phone) ? null : updateView.phone);
        } else {
            updateOffice.setPhone(officeDao.getById(updateView.id).getPhone());
        }
        updateOffice.setActive(updateView.isActive != null ? updateView.isActive : officeDao.getById(updateView.id).getActive());
        officeDao.update(updateOffice);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void save(OfficeToSave saveView) {
        validateSave(saveView);
        Office saveOffice = new Office();
        organizationDao.getById(saveView.orgId).addOffice(saveOffice);
        saveOffice.setName(saveView.name);
        saveOffice.setAddress(saveView.address);
        saveOffice.setPhone(saveView.phone);
        saveOffice.setActive(saveView.isActive);
        officeDao.save(saveOffice);
    }

    private void validateFilter(OfficeListFilter filterView) {
        if (filterView.orgId == null) {
            throw new WrongRequestException("Field \"orgId\" is null");
        }
    }

    private Function<Office, OfficeToUpdate> mapOffice() {
        return office -> {
            OfficeToUpdate view = new OfficeToUpdate();
            view.id = office.getId();
            view.name = office.getName();
            view.isActive = office.getActive();
            return view;
        };
    }

    private void validateUpdate(OfficeToUpdate updateView) {
        StringBuilder message = new StringBuilder("");
        if (updateView.name == null || !isNameValid(updateView.name)) {
            message.append("Field \"name\"is null or invalid. ");
        }
        if (updateView.address == null || !isAddressValid(updateView.address)) {
            message.append("Field \"address\"is null or invalid. ");
        }
        if (updateView.phone != null && !isPhoneValid(updateView.phone)) {
            message.append("Field \"phone\"is null or invalid. ");
        }
        if (message.length() > 0) {
            throw new WrongRequestException(message.toString().trim());
        }
        if (officeDao.getById(updateView.id) == null) {
            throw new RecordNotFoundException("Record with officeId " + updateView.id + " was not found");
        }
    }

    private void validateSave(OfficeToSave saveView) {
        StringBuilder message = new StringBuilder("");
        if (saveView.orgId == null) {
            message.append("Field \"orgId\"is null");
        }
        if (saveView.name == null || !isNameValid(saveView.name)) {
            message.append("Field \"name\"is null or invalid");
        }
        if (saveView.address == null || !isAddressValid(saveView.address)) {
            message.append("Field \"address\"is null or invalid");
        }
        if (saveView.phone == null || !isPhoneValid(saveView.phone)) {
            message.append("Field \"phone\"is null or invalid");
        }
        if (saveView.isActive == null) {
            message.append("Field \"isActive\"is null");
        }
        if (message.length() > 0) {
            throw new WrongRequestException(message.toString().trim());
        }
        if (organizationDao.getById(saveView.orgId) == null) {
            throw new RecordNotFoundException("Record with orgId " + saveView.orgId + " wasn't found");
        }
    }

    private boolean isNameValid(String name) {
        return name.matches("[a-zA-Zа-яА-Я\"-]{1,50}");
    }

    private boolean isAddressValid(String address) {
        return address.matches("[a-zA-Zа-яА-Я0-9\"-]{1,200}");
    }

    private boolean isPhoneValid(String phone) {
        return phone.matches("[0,9]{20}");
    }
}
