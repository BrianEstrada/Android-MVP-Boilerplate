package com.brianestrada.boilerplate

import android.app.Application
import com.brianestrada.boilerplate.injection.components.DaggerAppComponent
import com.brianestrada.boilerplate.injection.modules.AppModule
import com.squareup.leakcanary.LeakCanary
import timber.log.Timber

class App : Application() {
    var appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()

    override fun onCreate() {
        super.onCreate()
        initTimber()
        initLeakCanary()
    }

    private fun initTimber() {
        if (BuildConfig.DEBUG) {
            //Create our debug tree for using timber
            Timber.plant(Timber.DebugTree())
        }
    }

    private fun initLeakCanary() {
        if (BuildConfig.DEBUG) {
            if (LeakCanary.isInAnalyzerProcess(this)) {
                // This process is dedicated to LeakCanary for heap analysis.
                // You should not init your app in this process.
                return
            }
            LeakCanary.install(this)
        }
    }
}