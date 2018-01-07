package com.brianestrada.boilerplate.injection.modules

import android.content.Context
import com.brianestrada.boilerplate.App
import dagger.Module
import dagger.Provides

@Module
class AppModule(private val app: App) {

    @Provides
    fun provideAppContext(): Context {
        return app
    }

    @Provides
    fun provideApp(): App {
        return app
    }
}