package ru.bellintegrator.practice.view;

public class UserListFilter {

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
     * Код документа пользователя
     */
    public String docCode;

    /**
     * Код гражданства пользователя
     */
    public String citizenshipCode;

    @Override
    public String toString() {
        return "UserListFilter{" +
                "officeId=" + officeId +
                ", firstName='" + firstName + '\'' +
                ", secondName='" + secondName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", position='" + position + '\'' +
                ", docCode='" + docCode + '\'' +
                ", citizenshipCode='" + citizenshipCode + '\'' +
                '}';
    }
}
