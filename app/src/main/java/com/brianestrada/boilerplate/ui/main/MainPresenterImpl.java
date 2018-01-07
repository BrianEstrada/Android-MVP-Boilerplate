package com.brianestrada.boilerplate.ui.main;

import com.brianestrada.boilerplate.data.DataManager;
import com.brianestrada.boilerplate.ui.BasePresenterImpl;

import java.util.List;

import javax.inject.Inject;

import timber.log.Timber;

public final class MainPresenterImpl extends BasePresenterImpl<MainView> implements MainPresenter {
    private DataManager dataManager;

    private List<User> userList;

    @Inject
    public MainPresenterImpl(DataManager dataManager) {
        super();
        this.dataManager = dataManager;
    }

    @Override
    public void onStart(boolean firstRun) {
        super.onStart(firstRun);

        if (firstRun) {
            loadUsers();
        } else {
            passUsersToView();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onPresenterDestroyed() {
        super.onPresenterDestroyed();
    }

    @Override
    public void loadUsers() {
        this.userList = dataManager.getUsers();

        passUsersToView();
    }

    @Override
    public void saveUser() {
        String username = view.getUsername();

        if (username.isEmpty()) {
            return;
        }

        User user = new User(username);

        dataManager.saveUser(user);

        view.clearUsername();

        loadUsers();
    }

    @Override
    public void passUsersToView() {
        view.setUsers(userList);
    }

    @Override
    public void ping() {
        Timber.d("Ping");

        view.pong();
    }
}
