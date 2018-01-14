package com.brianestrada.boilerplate.injection.modules

import com.brianestrada.boilerplate.injection.scopes.ActivityScope
import com.brianestrada.boilerplate.loader.PresenterFactory
import com.brianestrada.boilerplate.ui.main.MainContract
import com.brianestrada.boilerplate.ui.main.MainPresenter
import dagger.Module
import dagger.Provides

@Module
class MainActivityModule {
    @Provides
    @ActivityScope
    fun providesPresenter(): PresenterFactory<MainContract.MainPresenter<MainContract.MainView>> {
        return PresenterFactory { MainPresenter() }
    }
}