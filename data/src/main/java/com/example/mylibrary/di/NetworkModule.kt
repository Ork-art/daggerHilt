package com.example.mylibrary.di

import com.example.domain.helper.Constants
import com.example.shareprefer.util.Api
import com.example.shareprefer.util.RetrofitClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    @Singleton
    fun provideGsonFactory():GsonConverterFactory= GsonConverterFactory.create()

    @Provides
    @Singleton
    fun provideHttpLogger(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().also {
        it.level = HttpLoggingInterceptor.Level.BODY
    }
    }

//    @Provides
//    @Singleton
//    fun provideAuthInterception():AuthInterception = AuthInterception()


    @Provides
    @Singleton
    fun provideOkHttpClient(logger: HttpLoggingInterceptor):OkHttpClient{
        return OkHttpClient.Builder()
            .connectTimeout(Constants.CONNECT_TIMEOUT, TimeUnit.SECONDS)
            .addInterceptor(logger)
            //.addInterceptor(authInterception)
            .build()
    }
    @Provides
    @Singleton
    fun provideRetrofit(client: OkHttpClient, factory:GsonConverterFactory):Retrofit{
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .client(client)
            .addConverterFactory(factory)
            .build()
    }

    @Provides
    @Singleton
    fun provideApi(retrofit: Retrofit):Api{
        return retrofit.create(Api::class.java)
    }

}