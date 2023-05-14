package com.justin.justmovie.di

import com.justin.justmovie.ApiService
import com.justin.justmovie.LoginService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Qualifier


@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class DefaultRetrofit

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class LoginRetrofit


@InstallIn(SingletonComponent::class)
@Module
object RetrofitModule {

    private val baseUrl = "https://api.themoviedb.org/"

    @DefaultRetrofit
    @Provides
    fun provideRetrofit(okhttpBuilder: OkHttpClient.Builder): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okhttpBuilder.build())
            .build()
    }

    @Provides
    fun provideOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient.Builder {
        return OkHttpClient.Builder().apply {
            addInterceptor(httpLoggingInterceptor)
        }
    }

    @Provides
    fun provideLogInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            setLevel(HttpLoggingInterceptor.Level.BODY)
        }
    }

    @LoginRetrofit
    @Provides
    fun provideLoginRetrofit(okhttpBuilder: OkHttpClient.Builder): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://fcb10186-f7c3-4b49-be0f-13d2ea7c213f.mock.pstmn.io")
            .addConverterFactory(GsonConverterFactory.create())
            .client(okhttpBuilder.build())
            .build()
    }

    @Provides
    fun provideApiService(@DefaultRetrofit retrofit: Retrofit): ApiService {
        return retrofit.create<ApiService>()
    }

    @Provides
    fun provideLoginService(@LoginRetrofit retrofit: Retrofit): LoginService {
        return retrofit.create<LoginService>()
    }
}