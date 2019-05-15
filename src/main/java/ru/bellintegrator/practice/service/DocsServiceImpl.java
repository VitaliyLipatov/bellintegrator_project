package ru.bellintegrator.practice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bellintegrator.practice.dao.DocTypeDao;
import ru.bellintegrator.practice.model.DocType;
import ru.bellintegrator.practice.view.DocTypeView;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Сервис, предоставляющий методы для получения справочной информации о документах
 */
@Service
public class DocsServiceImpl implements DocsService {

    private final DocTypeDao docTypeDao;

    /**
     * Конструктор
     *
     * @param docTypeDao DAO документов
     */
    @Autowired
    public DocsServiceImpl(DocTypeDao docTypeDao) {
        this.docTypeDao = docTypeDao;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = true)
    public List<DocTypeView> list() {
        List<DocType> list = docTypeDao.list();
        return list.stream().map(mapDoc()).collect(Collectors.toList());
    }

    private Function<DocType, DocTypeView> mapDoc() {
        return docType -> {
            DocTypeView view = new DocTypeView();
            view.name = docType.getName();
            view.code = docType.getCode();
            return view;
        };
    }
}
