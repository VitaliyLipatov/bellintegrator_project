package ru.bellintegrator.practice.view;

/**
 * Класс представления организации
 */
public class OrganizationView {

    /**
     * Идентификатор организации
     */
    public Long id;

    /**
     * Название организации
     */
    public String name;

    /**
     * Полное название организации
     */
    public String fullName;

    /**
     * ИНН организации
     */
    public String inn;

    /**
     * КПП организации
     */
    public String kpp;

    /**
     * Адрес местонахождения организации
     */
    public String address;

    /**
     * Телефон организации
     */
    public String phone;

    /**
     * Флаг, показывающий является ли организация действующей
     */
    public Boolean isActive;

    @Override
    public String toString() {
        return "OrganizationView{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", fullName='" + fullName + '\'' +
                ", inn='" + inn + '\'' +
                ", kpp='" + kpp + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", isActive=" + isActive +
                '}';
    }
}
