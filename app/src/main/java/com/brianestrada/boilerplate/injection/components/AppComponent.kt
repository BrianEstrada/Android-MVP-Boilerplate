package com.brianestrada.boilerplate.injection.components

import android.content.Context
import com.brianestrada.boilerplate.App
import com.brianestrada.boilerplate.injection.modules.AppModule
import com.brianestrada.boilerplate.injection.scopes.AppScope
import dagger.Component


@AppScope
@Component(modules = [AppModule::class])
interface AppComponent {
    fun getAppContext(): Context

    fun getApp(): App
}