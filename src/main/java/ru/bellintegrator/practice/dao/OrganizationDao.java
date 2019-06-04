package ru.bellintegrator.practice.dao;

import ru.bellintegrator.practice.model.Organization;

import java.util.List;

/**
 * DAO-слой для работы с организациями
 */
public interface OrganizationDao {

    /**
     * Возвращает отфильтрованный список организаций
     *
     * @param filter объект с данными фильтрации
     * @return отфильтрованный список организаций
     */
    List<Organization> list(Organization filter);

    /**
     * Возвращает организацию с указанным идентификатором
     *
     * @param id идентификатор организации
     * @return организация с указанным идентификатором
     */
    Organization getById(Long id);

    /**
     * Обновляет информацию об организации
     *
     * @param organization объект с новыми данными об организации
     */
    void update(Long id, Organization organization);

    /**
     * Сохраняет информацию о новой организации
     *
     * @param organization объект с данными о новой организации
     */
    void save(Organization organization);
}
