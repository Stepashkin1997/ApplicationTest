package ru.eltex.app.store;

import com.fasterxml.jackson.databind.ObjectMapper;
import ru.eltex.app.model.User;

import java.io.File;
import java.io.IOException;

public class UserService implements IUserStore {
    private final String PATH = "base.json";

    @Override
    public User readUserByName(String name) {
        ObjectMapper mapper = new ObjectMapper();
        User user = null;
        try {
            user = mapper.readValue(new File(PATH), User.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public void saveUser(User user) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            mapper.writeValue(new File(PATH), user);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("json created!");
    }
}
