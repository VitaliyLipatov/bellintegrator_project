package ru.bellintegrator.practice.view;

import java.util.Date;

/**
 * Класс обновления входных данных пользователя
 */
public class UserToUpdate {

    /**
     * Идентификатор пользователя
     */
    public Long id;

    /**
     * Идентификатор офиса, к которому относится пользователь
     */
    public Long officeId;

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
     * Код гражданства пользователя
     */
    public String citizenshipCode;

    /**
     * Флаг, подтверждающий идентификацию пользователя
     */
    public Boolean isIdentified;

    @Override
    public String toString() {
        return "UserToUpdate{" +
                "id=" + id +
                ", officeId=" + officeId +
                ", firstName='" + firstName + '\'' +
                ", secondName='" + secondName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", position='" + position + '\'' +
                ", phone='" + phone + '\'' +
                ", docName='" + docName + '\'' +
                ", docNumber='" + docNumber + '\'' +
                ", docDate='" + docDate + '\'' +
                ", citizenshipCode='" + citizenshipCode + '\'' +
                ", isIdentified=" + isIdentified +
                '}';
    }
}
