package com.brianestrada.boilerplate;

import android.app.Application;
import android.support.annotation.NonNull;

import com.brianestrada.boilerplate.injection.components.DaggerAppComponent;
import com.brianestrada.boilerplate.injection.modules.SharedPreferencesModule;
import com.squareup.leakcanary.LeakCanary;

import timber.log.Timber;

public final class App extends Application {
    private AppComponent mAppComponent;

    @Override
    public void onCreate() {
        super.onCreate();


        Timber.plant(new Timber.DebugTree());

        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }

        LeakCanary.install(this);
        // Normal app init code...

        mAppComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .sharedPreferencesModule(new SharedPreferencesModule())
                .build();
    }

    @NonNull
    public AppComponent getAppComponent() {
        return mAppComponent;
    }
}