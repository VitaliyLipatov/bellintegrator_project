package ru.bellintegrator.practice.service;

import ru.bellintegrator.practice.view.OfficeListFilter;
import ru.bellintegrator.practice.view.OfficeToSave;
import ru.bellintegrator.practice.view.OfficeToUpdate;

import java.util.List;

/**
 * Интерфейс, представляющий методы для работы с офисами
 */
public interface OfficeService {

    /**
     * Возвращает офис с указанным идентификатором
     *
     * @param id идентификатор офиса
     * @return офис с указанным идентификатором
     */
    OfficeToUpdate getById(Long id);

    /**
     * Возвращает отфильтрованный список офисов.
     *
     * @param filter фильтр для спиcка
     * @return отфильтрованный список
     */
    List<OfficeToUpdate> list(OfficeListFilter filter);

    /**
     * Обновляет сведения об офисе.
     *
     * @param view объект, содержащий сведения для обновления
     */
    void update(OfficeToUpdate view);

    /**
     * Сохраняет сведения о новом офисе.
     *
     * @param officeToSave объект, содержащий сведения о новом офисе
     */
    void save(OfficeToSave officeToSave);

    /**
     * Удаляет офис с указанным идентификатором
     *
     * @param id идентификатор офиса для удаления
     */
    void remove(Long id);
}
