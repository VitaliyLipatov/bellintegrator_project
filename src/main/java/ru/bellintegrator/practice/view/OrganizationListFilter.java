package ru.bellintegrator.practice.view;

/**
 * Класс для фильтрации списка организаций
 */
public class OrganizationListFilter {

    /**
     * Название организации
     */
    public String name;

    /**
     * ИНН организации
     */
    public String inn;

    /**
     * Флаг, показывающий является ли организация действующей
     */
    public boolean isActive;

    @Override
    public String toString() {
        return "OrganizationListFilter{" +
                "name='" + name + '\'' +
                ", inn='" + inn + '\'' +
                ", isActive=" + isActive +
                '}';
    }
}
