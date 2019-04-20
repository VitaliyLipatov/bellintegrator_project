package ru.bellintegrator.practice.view;

/**
 * Класс для сохранения офиса
 */
public class OfficeToSave {

    /**
     * Идентификатор орагнизации, к которой относится офис
     */
    public Long orgId;

    /**
     * Название офиса
     */
    public String name;

    /**
     * Адрес офиса
     */
    public String address;

    /**
     * Телефон офиса
     */
    public String phone;

    /**
     * Флаг, показывающий является ли офис действующим
     */
    public Boolean isActive;

    @Override
    public String toString() {
        return "OfficeToSave{" +
                "orgId=" + orgId +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", isActive=" + isActive +
                '}';
    }
}
