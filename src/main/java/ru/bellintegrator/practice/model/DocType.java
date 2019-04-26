package ru.bellintegrator.practice.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Справочник видов документов
 */
@Entity
@Table(name = "Doc_Type")
public class DocType {

    /**
     * Идентификатор
     */
    @Id
    @Column(name = "id")
    private Long id;

    /**
     * Названние документа
     */
    @Column(name = "name", length = 50, nullable = false)
    private String name;

    /**
     * Код документа
     */
    @Column(name = "code", length = 20, nullable = false)
    private String code;

    public DocType() {
    }

    //Getter and setter methods

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
