package com.brianestrada.boilerplate.ui.base

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.brianestrada.boilerplate.App
import com.brianestrada.boilerplate.injection.components.AppComponent
import com.brianestrada.boilerplate.loader.PresenterFactory

@SuppressLint("Registered")
abstract class BaseActivity<P : BasePresenter<V>, in V> : AppCompatActivity() {
    protected var presenter: P? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        injectDependencies()
        loadPresenter()
    }

    private fun loadPresenter() {
        presenter = getPresenterFactory().create()
        presenter?.onViewAttached( this as V, lifecycle)
    }

    private fun injectDependencies() {
        setupComponent((application as App).appComponent)
    }

    /**
     * Get the presenter factory implementation for this view
     *
     * @return the presenter factory
     */
    abstract fun getPresenterFactory(): PresenterFactory<P>

    /**
     * Setup the injection component for this view
     *
     * @param appComponent the app component
     */
    abstract fun setupComponent(appComponent: AppComponent)
}