package com.example.vpdmoney.di

import com.example.vpdmoney.data.remote.api.APIService
import com.example.vpdmoney.data.remote.api.APIService.Companion.BASE_URL
import com.example.vpdmoney.data.remote.repository.UsersRepositoryImplementation
import com.example.vpdmoney.domain.repository.UsersRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun providesLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    @Singleton
    @Provides
    fun providesConverterFactory(): Converter.Factory {
        return GsonConverterFactory.create()
    }

    @Singleton
    @Provides
    fun providesOkhttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
           // .callTimeout(300, TimeUnit.SECONDS)
            //.connectTimeout(300, TimeUnit.SECONDS)
           // .writeTimeout(300, TimeUnit.SECONDS)
           // .readTimeout(300, TimeUnit.SECONDS)

        return okHttpClient.build()
    }

    @Singleton
    @Provides
    fun providesRetrofit(
        converterFactory: Converter.Factory,
        okHttpClient: OkHttpClient
    ): Retrofit {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(converterFactory)
            .client(okHttpClient)
        return retrofit.build()
    }

    @Provides
    @Singleton
    fun provideMovieApiService(retrofit: Retrofit): APIService = retrofit
        .create(APIService::class.java)

    @Singleton
    @Provides
    fun provideRepository(
        apiService: APIService,
    ) = UsersRepositoryImplementation(
        api = apiService
    ) as UsersRepository
}
