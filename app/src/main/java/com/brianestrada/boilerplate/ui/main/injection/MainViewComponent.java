package com.brianestrada.boilerplate.ui.main.injection;

import com.brianestrada.boilerplate.ui.main.MainActivity;

import dagger.Component;

@Component(dependencies = AppComponent.class, modules = MainViewModule.class)
public interface MainViewComponent {
    void inject(MainActivity activity);
}
