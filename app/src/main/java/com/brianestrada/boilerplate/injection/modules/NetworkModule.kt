package com.brianestrada.boilerplate.injection.modules

import android.content.Context
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import timber.log.Timber

@Module(includes = [AppModule::class])
class NetworkModule {
    @Provides
    fun provideInterceptor(): HttpLoggingInterceptor {
        val httpLoggingInterceptor = HttpLoggingInterceptor { message -> Timber.d(message) }

        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BASIC

        return httpLoggingInterceptor
    }

    @Provides
    fun provideOkHttpCache(context: Context): Cache {
        val cacheSize = 10 * 1024 * 1024 // 10 MiB
        return Cache(context.cacheDir, cacheSize.toLong())
    }

    @Provides
    fun provideGson(): Gson {
        val gsonBuilder = GsonBuilder()
        gsonBuilder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
        gsonBuilder.setDateFormat("yyyy-MM-dd HH:mm:ss")
        return gsonBuilder.create()
    }


    @Provides
    fun provideConnectionSpec(): ConnectionSpec {
        return ConnectionSpec.Builder(ConnectionSpec.MODERN_TLS)
                .tlsVersions(TlsVersion.TLS_1_2)
                .cipherSuites(
                        CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_128_GCM_SHA256,
                        CipherSuite.TLS_ECDHE_RSA_WITH_AES_128_GCM_SHA256,
                        CipherSuite.TLS_DHE_RSA_WITH_AES_128_GCM_SHA256)
                .build()

    }

    @Provides
    fun provideOkHttpClient(cache: Cache, interceptor: HttpLoggingInterceptor, spec: ConnectionSpec): OkHttpClient {
        return OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .connectionSpecs(listOf(spec))
                .cache(cache)
                .build()
    }
}