package ru.bellintegrator.practice.controller;

import ru.bellintegrator.practice.service.UserService;
import ru.bellintegrator.practice.view.UserListFilter;
import ru.bellintegrator.practice.view.UserToSave;
import ru.bellintegrator.practice.view.UserToUpdate;
import ru.bellintegrator.practice.view.UserView;

import java.util.List;

public class UserServiceImpl implements UserService {
    @Override
    public List<UserView> list(UserListFilter filter) {
        return null;
    }

    @Override
    public UserView getById(Long id) {
        return null;
    }

    @Override
    public void update(UserToUpdate userToUpdate) {

    }

    @Override
    public void save(UserToSave userToSave) {

    }

    @Override
    public void remove(Long id) {

    }
}
