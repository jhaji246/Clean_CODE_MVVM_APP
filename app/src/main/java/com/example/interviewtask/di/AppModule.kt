package com.example.interviewtask.di

import com.example.interviewtask.core.utils.Constant.BASE_URL
import com.example.interviewtask.data.api.SchoolApi
import com.example.interviewtask.data.repository.SchoolRepositoryImpl
import com.example.interviewtask.domain.repository.SchoolRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class AppModule {

    @Provides
    @Singleton
    fun provideRetrofitInstance(): Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .build()

    @Provides
    @Singleton
    fun provideSchoolApi(retrofit: Retrofit): SchoolApi = retrofit.create(SchoolApi::class.java)

    @Provides
    @Singleton
    fun provideSchoolRepository(schoolApi: SchoolApi): SchoolRepository = SchoolRepositoryImpl(schoolApi)
}