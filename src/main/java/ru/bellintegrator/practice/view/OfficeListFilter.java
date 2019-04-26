package ru.bellintegrator.practice.view;

/**
 * Класс для фильтрации списка офисов
 */
public class OfficeListFilter {

    /**
     * Идентификатор организации, к которой относится офис
     */
    public Long orgId;

    /**
     * Название офиса
     */
    public String name;

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
        return "OfficeListFilter{" +
                "orgId=" + orgId +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", isActive=" + isActive +
                '}';
    }
}
