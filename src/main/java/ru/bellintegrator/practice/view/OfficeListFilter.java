package ru.bellintegrator.practice.view;

public class OfficeListFilter {

    /**
     * Название офиса
     */
    public String name;

    /**
     * Идентификатор организации, к которой относится офис
     */
    public Long orgId;

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
        return "{ orgId: " + orgId +
                "; name: \"" + name +
                "\"; phone: \"" + phone +
                "\"; isActive: " + isActive + " }";
    }
}
