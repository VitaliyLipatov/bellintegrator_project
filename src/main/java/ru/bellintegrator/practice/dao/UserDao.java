package ru.bellintegrator.practice.dao;

import ru.bellintegrator.practice.model.User;

import java.util.List;

/**
 * DAO-слой для работы с сотрудниками
 */
public interface UserDao {

    /**
     * Возвращает отфильтрованный список сотрудников
     *
     * @param filter объект с данными фильтрации
     * @return отфильтрованный список сотрудников
     */
    List<User> list(User filter);

    /**
     * Возвращает сотрудника с указанным идентификатором
     *
     * @param id идентификатор сотрудника
     * @return сотрудника с указанным идентификатором
     */
    User getById(Long id);

    /**
     * Обновляет информацию о сотруднике
     *
     * @param user объект с новыми данными о сотруднике
     */
    void update(User user);

    /**
     * Сохраняет информацию о новом сотруднике
     *
     * @param user объект с данными о новом сотруднике
     */
    void save(User user);
}
