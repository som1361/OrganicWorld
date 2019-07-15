package com.example.organicworld.DI.component

import com.example.organicworld.DI.Scope.ActivityScope
import com.example.organicworld.DI.module.ActivityModule
import com.example.organicworld.view.DetailActivity
import com.example.organicworld.view.SearchActivity
import dagger.Component

@ActivityScope
@Component(modules = [ActivityModule::class])
interface ActivityComponent {
    fun inject(target: SearchActivity)
    fun inject(target: DetailActivity)
}