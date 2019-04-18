package ru.bellintegrator.practice.view;

/**
 * Класс для сохранения офиса
 */
public class OfficeToSave {

    /**
     * Название офиса
     */
    public String name;

    /**
     * Идентификатор орагнизации, к которой относится офис
     */
    public Long orgId;

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
        return "{ name: \"" + name +
                "\"; orgId: " + orgId +
                "; address: \"" + address +
                "\"; phone: \"" + phone +
                "\"; isActive: " + isActive + " }";
    }
}
