package ru.bellintegrator.practice.dao;

import ru.bellintegrator.practice.model.DocType;

import java.util.List;

/**
 * DAO-слой для работы с документами
 */
public interface DocTypeDao {

    /**
     * Возвращает список документов, удостоверяющих личность и их кодов
     *
     * @return список документов
     */
    List<DocType> list();

    /**
     * Возвращает документ с указанным кодом
     *
     * @param code код документа
     * @return документ с указанным кодом
     */
    DocType getByCode(String code);
}
