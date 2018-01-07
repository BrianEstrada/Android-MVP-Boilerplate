package com.brianestrada.boilerplate.data;


import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import timber.log.Timber;

public class DataManagerImpl implements DataManager {
    private static final String SPK_USERS = "SPK_USERS";

    private SharedPreferences sharedPreferences;

    public DataManagerImpl(SharedPreferences sharedPreferences) {
        this.sharedPreferences = sharedPreferences;
    }

    @Override
    public void saveUser(User user) {
        List<User> users = getUsers();

        users.add(user);

        saveUsers(users);
    }

    @Override
    public void saveUsers(List<User> userList) {
        String userListString = new Gson().toJson(userList);

        sharedPreferences.edit()
                .putString(SPK_USERS, userListString)
                .apply();
    }

    @Override
    public List<User> getUsers() {
        String userListString = sharedPreferences.getString(SPK_USERS, null);

        if (userListString == null) {
            return new ArrayList<>();
        }


        Timber.d("User List: %s", userListString);

        Type userType = new TypeToken<List<User>>() {
        }.getType();

        return new Gson().fromJson(userListString, userType);
    }
}
