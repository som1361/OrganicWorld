package com.example.organicworld.DI.module

import android.app.Activity
import android.content.Context
import com.example.organicworld.DI.Scope.ActivityScope
import dagger.Module
import dagger.Provides

@Module
class ActivityModule(private val activity: Activity) {

    @Provides
    @ActivityScope
    fun providesContext(): Context = activity

//    /////ViewModel////////////////////////////////////
//    @Provides
//    @ActivityScope
//    fun providesViewModel(contentRepository: ContentRepository) = MainViewModel(contentRepository)
//
//    /////Model////////////////////////////////////////
//    @Provides
//    @ActivityScope
//    fun providesContentRepository(contentDao: ContentDao): ContentRepository = ContentDaoRepository(contentDao)

}
