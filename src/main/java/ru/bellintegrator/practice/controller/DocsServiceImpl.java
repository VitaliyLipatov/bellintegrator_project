package ru.bellintegrator.practice.controller;

import org.springframework.stereotype.Service;
import ru.bellintegrator.practice.service.DocsService;
import ru.bellintegrator.practice.view.DocTypeView;

import java.util.List;

@Service
public class DocsServiceImpl implements DocsService {
    @Override
    public List<DocTypeView> list() {
        return null;
    }
}
