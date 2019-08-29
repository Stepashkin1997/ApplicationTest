package ru.eltex.app.store;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.eltex.app.model.User;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;
import java.util.HashSet;
import java.util.Scanner;
import java.util.TreeMap;

@Service
public class UserService implements IUserStore {
    private final String PATH = "/tmp/base.txt";
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User readUserByName(String name) {
        try (Scanner reader = new Scanner(new FileReader(PATH))) {
            while (reader.hasNext()) {
                String line = reader.nextLine();
                var ar = line.split(":");
                if (ar[0].equals(name)) {
                    return new User(ar[0], ar[1]);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean saveUser(User user) {
        User oldUser =readUserByName(user.getLogin());
        if(oldUser==null) {
            try (FileWriter fileWriter = new FileWriter(PATH, true)) {
                fileWriter.write(user.getLogin() + ":" + passwordEncoder.encode(user.getPassword())
                        + System.getProperty("line.separator"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            return true;
        }
        else {
            return false;
        }
    }
}
