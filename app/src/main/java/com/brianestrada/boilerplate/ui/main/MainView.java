package com.brianestrada.boilerplate.ui.main;

import android.support.annotation.UiThread;

import com.brianestrada.boilerplate.models.User;

import java.util.List;

@UiThread
public interface MainView {
    void setUsers(List<User> userList);

    String getUsername();

    void clearUsername();

    void pong();
}
