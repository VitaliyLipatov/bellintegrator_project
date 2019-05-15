package ru.bellintegrator.practice.view;

/**
 * Класс представления вилов документов, удостоверяющих личность физического лица
 */
public class DocTypeView {

    /**
     * Название документа
     */
    public String name;

    /**
     * Код документа
     */
    public String code;

    @Override
    public String toString() {
        return "DocTypeView{" +
                "name='" + name + '\'' +
                ", code='" + code + '\'' +
                '}';
    }
}
