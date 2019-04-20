package ru.bellintegrator.practice.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.bellintegrator.practice.service.UserService;
import ru.bellintegrator.practice.view.UserListFilter;
import ru.bellintegrator.practice.view.UserToSave;
import ru.bellintegrator.practice.view.UserToUpdate;
import ru.bellintegrator.practice.view.UserView;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

/**
 * Класс контроллера для работы с пользователями
 */
@RestController
@RequestMapping(value = "/api/user", produces = APPLICATION_JSON_VALUE)
public class UserController {

    private final UserService userService;

    /**
     * Конструктор
     *
     * @param userService сервис, предоставляющий методы работы с пользователями
     */
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Возвращает отфильтрованный список пользователей
     *
     * @param filter фильтр для списка
     * @return отфильтрованный список
     */
    @ApiOperation(value = "Get user list by filter", nickname = "getUserListByFilter", httpMethod = "POST")
    @PostMapping("/list")
    public List<UserView> list(@RequestBody UserListFilter filter) {
        return userService.list(filter);
    }

    /**
     * Возвращает пользователя с указанным идентификатором
     *
     * @param id идентификатор пользователя
     * @return пользователя с указанным идентификатором
     */
    @ApiOperation(value = "Get user by id", nickname = "getUserById", httpMethod = "GET")
    @GetMapping("/id")
    public UserView getById(@PathVariable Long id) {
        return userService.getById(id);
    }

    /**
     * Обновляет информацию о пользователе
     *
     * @param userToUpdate объект, содержащий информацию для обновления
     */
    @ApiOperation(value = "Update user", nickname = "updateUser", httpMethod = "POST")
    @PostMapping("/update")
    public void update(@RequestBody UserToUpdate userToUpdate) {
        userService.update(userToUpdate);
    }

    /**
     * Сохраняет информацию о новом пользователе
     *
     * @param userToSave объект, содержащий информацию о новом пользователе
     */
    @ApiOperation(value = "Save new user", nickname = "saveUser", httpMethod = "POST")
    @PostMapping("/save")
    public void save(@RequestBody UserToSave userToSave) {
        userService.save(userToSave);
    }

    /**
     * Удаляет пользователя с указанным идентификатором
     *
     * @param id идентификатор пользователя для удаления
     */
    @ApiOperation(value = "Remove user", nickname = "removeUser", httpMethod = "POST")
    @PostMapping("/remove")
    public void remove(@PathVariable Long id) {
        userService.remove(id);
    }
}
