package com.example.organicworld.DI.module

import android.app.Application
import android.content.Context
import com.example.organicworld.DI.Scope.AppScope
import dagger.Module
import dagger.Provides

@Module
class AppModule(private val app:Application) {

    @Provides
    @AppScope
    fun providesContext(): Context = app

//    @Provides
//    @AppScope
//    fun providesContentDao(context: Context): ContentDao = ContentDao(context)
}