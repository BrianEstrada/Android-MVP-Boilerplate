package com.brianestrada.boilerplate.injection.components

import com.brianestrada.boilerplate.injection.modules.MainActivityModule
import com.brianestrada.boilerplate.injection.scopes.ActivityScope
import com.brianestrada.boilerplate.ui.main.MainActivity
import dagger.Component

@ActivityScope
@Component(dependencies = [AppComponent::class], modules = [MainActivityModule::class])
interface ActivityComponent {
    fun inject(activity: MainActivity)
}