package com.example.currencyratechange.di

import android.content.Context
import com.example.currencyratechange.ui.util.SharedPrefHelper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun providersSharedPrefHelper(@ApplicationContext context: Context) =
        SharedPrefHelper(context)
}
