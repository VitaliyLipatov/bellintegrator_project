package ru.bellintegrator.practice.view;

/**
 * Класс представления офиса
 */
public class OfficeView {

    /**
     * Идентификатор офиса
     */
    public Long id;

    /**
     * Идентификатор организации, которой принадлежит офис
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
     * Номер телефона офиса
     */
    public String phone;

    /**
     * Флаг, показывающий является ли офис действующим
     */
    public Boolean isActive;

    @Override
    public String toString() {
        return "{ id:" + id +
                "; name: \"" + name +
                "\"; orgId: " + orgId +
                "; address: \"" + address +
                "\"; phone: \"" + phone +
                "\"; isActive: " + isActive + " }";
    }
}
