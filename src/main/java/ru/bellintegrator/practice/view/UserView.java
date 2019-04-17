package ru.bellintegrator.practice.view;

/**
 * Класс представления пользователя
 */
public class UserView {

    /**
     * Идентификатор пользователя
     */
    public Long id;

    /**
     * Идентификатор офиса, за которым закреплен пользователь
     */
    public Long officeId;

    /**
     * Идентификатор документа пользователя
     */
    public Long docId;

    /**
     * Идентификатор страны пользователя
     */
    public Long countryId;

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
     * Код города пользователя
     */
    public String citizenshipCode;

    /**
     * Флаг, подтверждающий идентификацию пользователя
     */
    public Boolean isIdentified;

    @Override
    public String toString() {
        return "{ id: " + id +
                "; firstName: \"" + firstName +
                "\"; lastName: \"" + secondName +
                "\"; middleName: \"" + middleName +
                "\"; officeId: " + officeId +
                "\"; docId: " + docId +
                "\"; countryId: " + countryId +
                "; position: \"" + position +
                "\"; phone: \"" + phone +
                "\"; citizenshipCode: " + citizenshipCode +
                "\"; isIdentified: " + isIdentified + "} ";
    }
}
