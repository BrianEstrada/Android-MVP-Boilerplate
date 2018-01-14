package com.brianestrada.boilerplate.loader;

import android.support.annotation.NonNull;

import com.brianestrada.boilerplate.ui.base.BasePresenter;

public interface PresenterFactory<T> {
    @NonNull
    T create();
}
