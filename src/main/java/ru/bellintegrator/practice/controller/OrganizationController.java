package ru.bellintegrator.practice.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.bellintegrator.practice.service.OrganizationService;
import ru.bellintegrator.practice.view.OrganizationListFilter;
import ru.bellintegrator.practice.view.OrganizationToSave;
import ru.bellintegrator.practice.view.OrganizationView;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

/**
 * Класс контроллера для работы с организациями
 */
@RestController
@RequestMapping(value = "/api/organization", produces = APPLICATION_JSON_VALUE)
public class OrganizationController {

    private final OrganizationService organizationService;

    /**
     * Конструктор
     *
     * @param organizationService сервис, предоставляющий методы работы с организациями
     */
    @Autowired
    public OrganizationController(OrganizationService organizationService) {
        this.organizationService = organizationService;
    }

    /**
     * Возвращает отфильтрованный список организаций
     *
     * @param filter фильтр для списка
     * @return отфильтрованный список
     */
    @ApiOperation(value = "Get organization list by filter", nickname = "getOrganizationListById", httpMethod = "POST")
    @PostMapping("/list")
    public List<OrganizationView> list(@RequestBody OrganizationListFilter filter) {
        return organizationService.list(filter);
    }

    /**
     * Возвращает организацию с указанным идентификатором
     *
     * @param id идентификатор организации
     * @return организацию с указанным идентификатором
     */
    @ApiOperation(value = "Get organization by id", nickname = "getOrganizationById", httpMethod = "GET")
    @GetMapping("/id")
    public OrganizationView getById(@PathVariable Long id) {
        return organizationService.getById(id);
    }

    /**
     * Обновляет информацию об организации
     *
     * @param view объект, содержащий информацию для обновления
     */
    @ApiOperation(value = "Update organization", nickname = "updateOrganiztion", httpMethod = "POST")
    @PostMapping("/update")
    public void update(@RequestBody OrganizationView view) {
        organizationService.update(view);
    }

    /**
     * Сохраняет информацию о новой организации
     *
     * @param organizationToSave объект, содержащий информацию о новой организации
     */
    @ApiOperation(value = "Save organizaion", nickname = "saveOrganization", httpMethod = "POST")
    @PostMapping("/save")
    public void save(@RequestBody OrganizationToSave organizationToSave) {
        organizationService.save(organizationToSave);
    }
}
