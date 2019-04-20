package ru.bellintegrator.practice.service;


import ru.bellintegrator.practice.view.DocTypeView;

import java.util.List;

/**
 * Интерфейс, предоставляющий методы для работы со справочником документов
 */
public interface DocsService {

    /**
     * Возвращает список документов, удостоверяющих личность и их кодов
     *
     * @return список документов и их кодов
     */
    List<DocTypeView> list();
}
