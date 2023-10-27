package ru.appline.logic;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Model implements Serializable {
    private static final Model instance = new Model ();

    private final Map<Integer, User> model;

    public static Model getInstance() {
        return instance;
    }

    private Model() {
        model = new HashMap<>();

        model.put(1, new User("Alex", "Akhmetov", 5555));
        model.put(2, new User("Eugen", "Skor", 6666));
        model.put(3, new User("Kostya", "Brag", 7777));
    }

    public void add(User user, int id) {
        model.put(id, user);
    }

    public Map<Integer, User> getFromList() {
        return model;
    }

    public void deleteFromList(int id) {
        model.remove(id);
    }

    public void change(User user,int id) {
        model.put(id, user);
    }

}
