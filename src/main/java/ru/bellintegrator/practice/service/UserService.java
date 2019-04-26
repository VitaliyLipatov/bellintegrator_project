package ru.bellintegrator.practice.service;

import ru.bellintegrator.practice.view.UserListFilter;
import ru.bellintegrator.practice.view.UserToSave;
import ru.bellintegrator.practice.view.UserToUpdate;
import ru.bellintegrator.practice.view.UserView;

import java.util.List;

/**
 * Интерфейс, предоставляющий методы для работы с пользователями
 */
public interface UserService {

    /**
     * Возвращает отфильтрованный список пользователей
     *
     * @param filter фильтр для списка
     * @return отфильтрованный список
     */
    List<UserView> list(UserListFilter filter);

    /**
     * Возвращает пользователя с указанным идентификатором
     *
     * @param id идентификатор пользователя
     * @return пользователя с указанным идентификатором
     */
    UserView getById(Long id);

    /**
     * Обновляет информацию о пользователе
     *
     * @param userToUpdate объект, содержащий информацию для обновления
     */
    void update(UserToUpdate userToUpdate);

    /**
     * Сохраняет информацию о новом пользователе
     *
     * @param userToSave объект, содержащий информацию о новом пользователе
     */
    void save(UserToSave userToSave);
}
