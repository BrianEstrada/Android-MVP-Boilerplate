package com.brianestrada.boilerplate.ui.base

import android.arch.lifecycle.Lifecycle

abstract class BasePresenterImpl<V> : BasePresenter<V> {
    var view: V? = null

    override fun onViewAttached(view: V, lifecycle: Lifecycle) {
        this.view = view
        lifecycle.addObserver(this)
    }

    override fun onResume() {

    }

    override fun onStart() {
    }

    override fun onStop() {
    }

    override fun onDestroy() {
    }
}