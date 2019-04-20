package ru.bellintegrator.practice.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.bellintegrator.practice.service.DocsService;
import ru.bellintegrator.practice.view.DocTypeView;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

/**
 * Класс контроллера для работы со справочником документов
 */
@RestController
@RequestMapping(value = "/api", produces = APPLICATION_JSON_VALUE)
public class DocsController {

    private final DocsService docsService;

    /**
     * Конструктор
     *
     * @param docsService сервис, предоставляющий методы получения справочной информации о документах
     */
    @Autowired
    public DocsController(DocsService docsService) {
        this.docsService = docsService;
    }

    /**
     * Возвращает список документов, удостоверяющих личность и их кодов
     *
     * @return список документов и их кодов
     */
    @ApiOperation(value = "Get documents list", nickname = "getDocumentsList", httpMethod = "POST")
    @PostMapping("/docs")
    public List<DocTypeView> list() {
        return docsService.list();
    }
}
