package com.brianestrada.boilerplate.ui.main;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.widget.EditText;

import com.brianestrada.boilerplate.R;
import com.brianestrada.boilerplate.adapters.UsersAdapter;
import com.brianestrada.boilerplate.loader.PresenterFactory;
import com.brianestrada.boilerplate.ui.BaseActivity;
import com.brianestrada.boilerplate.ui.main.injection.DaggerMainViewComponent;
import com.brianestrada.boilerplate.ui.main.injection.MainViewModule;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import timber.log.Timber;

public final class MainActivity extends BaseActivity<MainPresenter, MainView> implements MainView {
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.etUsername)
    EditText etUsername;

    @Inject
    PresenterFactory<MainPresenter> presenterFactory;

    UsersAdapter adapter;
    List<User> userList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        setupRecycler();

        setupEnterListener();
    }

    private void setupRecycler() {
        userList = new ArrayList<>();

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);

        adapter = new UsersAdapter(userList);

        recyclerView.setLayoutManager(layoutManager);

        recyclerView.addItemDecoration(dividerItemDecoration);

        recyclerView.setAdapter(adapter);
    }

    private void setupEnterListener() {
        etUsername.setOnKeyListener((v, keyCode, event) -> {
            Timber.d("Event");
            if (event.getAction() == KeyEvent.ACTION_UP) {
                if (keyCode == KeyEvent.KEYCODE_ENTER) {

                    Timber.d("User Enter");

                    presenter.saveUser();

                    return true;
                }
            }
            return false;
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        presenter.ping();
    }

    @Override
    protected void setupComponent(@NonNull AppComponent parentComponent) {
        DaggerMainViewComponent.builder()
                .appComponent(parentComponent)
                .mainViewModule(new MainViewModule())
                .build()
                .inject(this);
    }

    @NonNull
    @Override
    protected PresenterFactory<MainPresenter> getPresenterFactory() {
        return presenterFactory;
    }

    @Override
    public void setUsers(List<User> data) {
        userList.clear();

        userList.addAll(data);

        adapter.notifyDataSetChanged();
    }

    @Override
    public String getUsername() {
        return etUsername.getText().toString();
    }

    @Override
    public void clearUsername() {
        etUsername.setText("");
    }

    @Override
    public void pong() {
        Timber.d("Pong");
    }
}
