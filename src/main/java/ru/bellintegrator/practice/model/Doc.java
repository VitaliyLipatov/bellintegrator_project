package ru.bellintegrator.practice.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.FetchType;

/**
 * Документ сотрудника
 */
@Entity
@Table(name = "Doc")
public class Doc {

    /**
     * Идентификатор
     */
    @Id
    @Column(name = "id")
    private Long id;

    /**
     * Номер документа
     */
    @Column(name = "number", length = 50, nullable = false)
    private String number;

    /**
     * Дата документа
     */
    @Column(name = "date", length = 50, nullable = false)
    private String date;

    /**
     * Вид документа
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "type_id", nullable = false)
    private DocType docType;

    /**
     * Конструктор для Hibernate
     */
    public Doc() {
    }

    //Getter and setter methods

    public Long getId() {
        return id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public DocType getDocType() {
        return docType;
    }

    public void setDocType(DocType docType) {
        this.docType = docType;
    }
}
