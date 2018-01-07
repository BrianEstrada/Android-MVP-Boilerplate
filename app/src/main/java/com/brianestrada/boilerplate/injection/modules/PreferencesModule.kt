package com.brianestrada.boilerplate.injection.modules

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import com.brianestrada.boilerplate.data.preferences.Preferences
import com.brianestrada.boilerplate.data.preferences.PreferencesImpl
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import timber.log.Timber

@Module(includes = [AppModule::class])
class PreferencesModule {
    @Provides
    fun providesSharedPreferences(context: Context): SharedPreferences {
        return PreferenceManager.getDefaultSharedPreferences(context)
    }

    @Provides
    fun providesPreferences(sharedPreferences: SharedPreferences): Preferences {
        return PreferencesImpl(sharedPreferences)
    }
}