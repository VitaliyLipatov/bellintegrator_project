package ru.bellintegrator.practice.view;

/**
 * Класс представления справочника стран
 */
public class CountryView {

    /**
     * Наименование страны
     */
    public String country;

    /**
     * Код страны
     */
    public Integer code;

    @Override
    public String toString() {
        return "CountryView{" +
                "country='" + country + '\'' +
                ", code=" + code +
                '}';
    }
}
