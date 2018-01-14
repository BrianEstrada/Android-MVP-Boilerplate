package com.brianestrada.boilerplate.ui.main

import android.arch.lifecycle.Lifecycle
import com.brianestrada.boilerplate.ui.base.BasePresenter
import com.brianestrada.boilerplate.ui.base.BasePresenterImpl
import timber.log.Timber

class MainPresenter : BasePresenterImpl<MainContract.MainView>(), MainContract.MainPresenter<MainContract.MainView> {
    override fun onViewAttached(view: MainContract.MainView, lifecycle: Lifecycle) {
        super.onViewAttached(view, lifecycle)
        Timber.d("onViewAttached")
    }

    override fun onStart() {
        super.onStart()
        Timber.d("onStart")
    }

    override fun onResume() {
        super.onResume()
        Timber.d("onResume")
    }

    override fun onStop() {
        super.onStop()
        Timber.d("onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Timber.d("onDestroy")
    }
}