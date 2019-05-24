package ru.bellintegrator.practice.view;

import java.util.Date;

/**
 * Класс представления пользователя
 */
public class UserView {

    /**
     * Идентификатор пользователя
     */
    public Long id;

    /**
     * Имя пользователя
     */
    public String firstName;

    /**
     * Фамилия пользователя
     */
    public String secondName;

    /**
     * Отчество пользователя
     */
    public String middleName;

    /**
     * Должность пользователя
     */
    public String position;

    /**
     * Телефон пользователя
     */
    public String phone;

    /**
     * Наименование документа пользователя
     */
    public String docName;

    /**
     * Номер документа пользователя
     */
    public String docNumber;

    /**
     * Дата документа пользователя
     */
    public Date docDate;

    /**
     * Гражданство пользователя
     */
    public String citizenshipName;

    /**
     * Код гражданства пользователя пользователя
     */
    public String citizenshipCode;

    /**
     * Флаг, подтверждающий идентификацию пользователя
     */
    public Boolean isIdentified;

    @Override
    public String toString() {
        return "UserView{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", secondName='" + secondName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", position='" + position + '\'' +
                ", phone='" + phone + '\'' +
                ", docName='" + docName + '\'' +
                ", docNumber='" + docNumber + '\'' +
                ", docDate='" + docDate + '\'' +
                ", citizenshipName='" + citizenshipName + '\'' +
                ", citizenshipCode='" + citizenshipCode + '\'' +
                ", isIdentified=" + isIdentified +
                '}';
    }
}
