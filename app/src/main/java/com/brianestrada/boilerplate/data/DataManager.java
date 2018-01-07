package com.brianestrada.boilerplate.data;


import java.util.List;

public interface DataManager {
    void saveUser(User user);

    void saveUsers(List<User> userList);

    List<User> getUsers();
}
