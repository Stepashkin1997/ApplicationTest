package ru.eltex.app.store;

import ru.eltex.app.model.User;

public interface IUserStore {
    User readUserByName(String name);
    boolean saveUser(User obj);
}
