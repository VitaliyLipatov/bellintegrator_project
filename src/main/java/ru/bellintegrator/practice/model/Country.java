package ru.bellintegrator.practice.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Страна гражданства
 */
@Entity
@Table(name = "Country")
public class Country {

    /**
     * Идентификатор
     */
    @Id
    @Column(name = "id")
    private Long id;

    /**
     * Название страны
     */
    @Column(name = "name", length = 50, nullable = false)
    private String name;

    /**
     * Код страны
     */
    @Column(name = "code", length = 20, nullable = false)
    private String code;

    /**
     * Конструктор для Hibernate
     */
    public Country() {
    }

    //Getter and setter methods

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
