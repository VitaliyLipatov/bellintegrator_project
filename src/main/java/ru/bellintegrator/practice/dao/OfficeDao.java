package ru.bellintegrator.practice.dao;

import ru.bellintegrator.practice.model.Office;

import java.util.List;

/**
 * DAO-слой для работы с офисами
 */
public interface OfficeDao {

    /**
     * Возвращает отфильтрованный список офисов
     *
     * @param filter объект с данными фильтрации
     * @return отфильтрованный список офисов
     */
    List<Office> list(Long orgId, Office filter);

    /**
     * Возвращает офис с указанным идентификатором
     *
     * @param id идентификатор офиса
     * @return офис с указанным идентификатором
     */
    Office getById(Long id);

    /**
     * Обновляет информацию об офисе
     *
     * @param office объект с новыми данными об офисе
     */
    void update(Long id, Office office);

    /**
     * Сохраняет новый офис
     *
     * @param office объект с данными о новом офисе
     */
    void save(Office office);
}
