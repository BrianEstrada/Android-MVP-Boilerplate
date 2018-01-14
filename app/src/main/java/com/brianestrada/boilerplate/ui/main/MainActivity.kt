package com.brianestrada.boilerplate.ui.main

import android.os.Bundle
import com.brianestrada.boilerplate.R
import com.brianestrada.boilerplate.injection.components.AppComponent
import com.brianestrada.boilerplate.injection.components.DaggerActivityComponent
import com.brianestrada.boilerplate.loader.PresenterFactory
import com.brianestrada.boilerplate.ui.base.BaseActivity
import javax.inject.Inject

class MainActivity : BaseActivity<MainContract.MainPresenter<MainContract.MainView>, MainContract.MainView>(), MainContract.MainView {
    @Inject
    lateinit var pFactory: PresenterFactory<MainContract.MainPresenter<MainContract.MainView>>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun getPresenterFactory(): PresenterFactory<MainContract.MainPresenter<MainContract.MainView>> {
        return pFactory
    }

    override fun setupComponent(appComponent: AppComponent) {
        DaggerActivityComponent.builder()
                .appComponent(appComponent)
                .build()
                .inject(this)
    }
}