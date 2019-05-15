package ru.bellintegrator.practice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import ru.bellintegrator.practice.dao.CountriesDao;
import ru.bellintegrator.practice.dao.OfficeDao;
import ru.bellintegrator.practice.dao.UserDao;
import ru.bellintegrator.practice.exception.RecordNotFoundException;
import ru.bellintegrator.practice.exception.WrongRequestException;
import ru.bellintegrator.practice.model.Doc;
import ru.bellintegrator.practice.model.DocType;
import ru.bellintegrator.practice.model.User;
import ru.bellintegrator.practice.view.UserListFilter;
import ru.bellintegrator.practice.view.UserToSave;
import ru.bellintegrator.practice.view.UserToUpdate;
import ru.bellintegrator.practice.view.UserView;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserDao userDao;
    private final OfficeDao officeDao;
    private final CountriesDao countriesDao;

    /**
     * Конструктор
     *
     * @param userDao      DAO сотрудников
     * @param officeDao    DAO офисов
     * @param countriesDao DAO стран
     */
    @Autowired
    public UserServiceImpl(UserDao userDao, OfficeDao officeDao, CountriesDao countriesDao) {
        this.userDao = userDao;
        this.officeDao = officeDao;
        this.countriesDao = countriesDao;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = true)
    public List<UserView> list(UserListFilter filterView) {
        validateFilter(filterView);
        User filter = new User();
        filter.setOffice(officeDao.getById(filterView.officeId));
        filter.setFirstName(filterView.firstName);
        filter.setSecondName(filterView.secondName);
        filter.setMiddleName(filterView.middleName);
        filter.setPosition(filterView.position);
        Doc doc = new Doc();
        DocType docType = new DocType();
        docType.setCode(filterView.docCode);
        doc.setDocType(docType);
        filter.setDoc(doc);
        filter.setCountry(countriesDao.getByCode(filterView.citizenshipCode));
        List<User> list = userDao.list(filter);
        return list.stream().map(mapUser()).collect(Collectors.toList());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = true)
    public UserView getById(Long userId) {
        if (userId == null) {
            throw new WrongRequestException("Field \"userId\" is null");
        }
        User user = userDao.getById(userId);
        if (user == null) {
            throw new RecordNotFoundException("Record with id" + userId + "wasn't found ");
        }
        UserView userView = new UserView();
        userView.id = user.getId();
        userView.firstName = user.getFirstName();
        userView.secondName = user.getSecondName();
        userView.middleName = user.getMiddleName();
        userView.position = user.getPosition();
        userView.phone = user.getPhone();
        userView.docName = user.getDoc().getDocType().getName();
        userView.docNumber = user.getDoc().getNumber();
        userView.docDate = user.getDoc().getDate();
        userView.citizenshipName = user.getCountry().getName();
        userView.citizenshipCode = user.getCountry().getCode();
        userView.isIdentified = user.getIdentified();
        return userView;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void update(UserToUpdate updateView) {
        validateUpdate(updateView);
        User updateUser = new User();
        updateUser.setOffice(officeDao.getById(updateView.id));
        updateUser.setFirstName(updateView.firstName);
        updateUser.setSecondName(updateView.secondName);
        updateUser.setMiddleName(updateView.middleName);
        updateUser.setPosition(updateView.position);
        if (updateView.phone != null) {
            updateUser.setPhone(StringUtils.isEmpty(updateView.phone) ? null : updateView.phone);
        } else {
            updateUser.setPhone(userDao.getById(updateView.id).getPhone());
        }
        Doc doc = new Doc();
        DocType docType = new DocType();
        docType.setName(updateView.docName);
        doc.setDocType(docType);
        updateUser.setDoc(doc);
        doc.setNumber(updateView.docNumber);
        updateUser.setDoc(doc);
        doc.setDate(updateView.docDate);
        updateUser.setDoc(doc);
        updateUser.setCountry(countriesDao.getByCode(updateView.citizenshipCode));
        updateUser.setIdentified(updateView.isIdentified);
        userDao.update(updateUser);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void save(UserToSave saveView) {
        validateSave(saveView);
        User saveUser = new User();
        officeDao.getById(saveView.officeId).addUser(saveUser);
        saveUser.setFirstName(saveView.firstName);
        saveUser.setSecondName(saveView.secondName);
        saveUser.setMiddleName(saveView.middleName);
        saveUser.setPosition(saveView.position);
        saveUser.setPhone(saveView.phone);
        Doc doc = new Doc();
        DocType docType = new DocType();
        docType.setCode(saveView.docCode);
        doc.setDocType(docType);
        saveUser.setDoc(doc);
        docType.setName(saveView.docName);
        doc.setDocType(docType);
        saveUser.setDoc(doc);
        doc.setNumber(saveView.docNumber);
        saveUser.setDoc(doc);
        doc.setDate(saveView.docDate);
        saveUser.setDoc(doc);
        saveUser.setCountry(countriesDao.getByCode(saveView.citizenshipCode));
        saveUser.setIdentified(saveView.isIdentified);
        userDao.save(saveUser);
    }

    private void validateFilter(UserListFilter filterView) {
        if (filterView.officeId == null) {
            throw new WrongRequestException("Field \"officeId\"is null ");
        }
    }

    private Function<User, UserView> mapUser() {
        return user -> {
            UserView view = new UserView();
            view.id = user.getId();
            view.firstName = user.getFirstName();
            view.secondName = user.getSecondName();
            view.middleName = user.getMiddleName();
            view.position = user.getPosition();
            return view;
        };
    }

    private void validateUpdate(UserToUpdate updateView) {
        StringBuilder message = new StringBuilder("");
        if (updateView.id == null) {
            message.append("Field \"id\" is null. ");
        }
        if (updateView.firstName == null || !isFirstNameValid(updateView.firstName)) {
            message.append("Field \"firstName\" is null or invalid. ");
        }
        if (updateView.secondName != null && !isSecondNameValid(updateView.secondName)) {
            message.append("Field \"secondName\" is invalid. ");
        }
        if (updateView.middleName != null && !isMiddleNameValid(updateView.middleName)) {
            message.append("Field \"middleName\" is invalid. ");
        }
        if (updateView.position == null || !isPositionValid(updateView.position)) {
            message.append("Field \"position\" is null or invalid. ");
        }
        if (updateView.phone != null && !isPhoneValid(updateView.phone)) {
            message.append("Field \"phone\" is invalid. ");
        }
        if (updateView.docNumber != null) {
            message.append("Field \"docNumber\" is invalid. ");
        }
        if (message.length() > 0) {
            throw new WrongRequestException(message.toString().trim());
        }
        if (userDao.getById(updateView.id) == null) {
            message.append("Record with id = ").append(updateView.id).append(" was not found in Users. ");
        }
        if (countriesDao.getByCode(updateView.citizenshipCode) == null) {
            message.append("Country with code = ").append(updateView.citizenshipCode).append(" was not found. ");
        }
        if (message.length() > 0) {
            throw new RecordNotFoundException(message.toString().trim());
        }
    }

    private void validateSave(UserToSave saveView) {
        StringBuilder message = new StringBuilder("");
        if (saveView.officeId == null) {
            message.append("Field \"id\" is null. ");
        }
        if (saveView.firstName == null || !isFirstNameValid(saveView.firstName)) {
            message.append("Field \"firstName\" is null or invalid. ");
        }
        if (saveView.secondName != null && !isSecondNameValid(saveView.secondName)) {
            message.append("Field \"secondName\" is invalid. ");
        }
        if (saveView.middleName != null && !isMiddleNameValid(saveView.middleName)) {
            message.append("Field \"middleName\" is invalid. ");
        }
        if (saveView.position == null || !isPositionValid(saveView.position)) {
            message.append("Field \"position\" is null or invalid. ");
        }
        if (saveView.phone != null && !isPhoneValid(saveView.phone)) {
            message.append("Field \"phone\" is invalid. ");
        }
        if (saveView.docNumber != null) {
            message.append("Field \"docNumber\" is invalid. ");
        }
        if (saveView.docDate == null) {
            message.append("Field \"docDate\" is null. ");
        }
        if (saveView.citizenshipCode == null) {
            message.append("Field \"citizenshipCode\" is null. ");
        }
        if (saveView.isIdentified == null) {
            message.append("Field \"isIdentified\" is null.");
        }
        if (message.length() > 0) {
            throw new WrongRequestException(message.toString().trim());
        }
        if (officeDao.getById(saveView.officeId) == null) {
            message.append("Office with id = ").append(saveView.officeId).append(" was not found. ");
        }
        if (userDao.getById(saveView.officeId) == null) {
            message.append("Record with id = ").append(saveView.officeId).append(" was not found in Users. ");
        }
        if (countriesDao.getByCode(saveView.citizenshipCode) == null) {
            message.append("Country with code = ").append(saveView.citizenshipCode).append(" was not found.");
        }
        if (message.length() > 0) {
            throw new RecordNotFoundException(message.toString().trim());
        }
    }

    private boolean isFirstNameValid(String name) {
        return name.matches("[a-zA-Zа-яА-Я\"-]{1,50}");
    }

    private boolean isSecondNameValid(String name) {
        return name.matches("[a-zA-Zа-яА-Я\"-]{1,50}");
    }

    private boolean isMiddleNameValid(String name) {
        return name.matches("[a-zA-Zа-яА-Я\"-]{1,50}");
    }

    private boolean isPositionValid(String position) {
        return position.matches("[A-Za-zА-Яа-я ,-]{1,50}");
    }

    private boolean isPhoneValid(String phone) {
        return phone.matches("[0,9]{20}");
    }
}
