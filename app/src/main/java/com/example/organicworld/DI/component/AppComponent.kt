package com.example.organicworld.DI.component

import com.example.organicworld.DI.Scope.AppScope
import com.example.organicworld.DI.module.AppModule
import com.example.organicworld.application.OrganicApplication

import dagger.Component
@AppScope
@Component(modules = [AppModule::class])
interface AppComponent {
fun inject(target:OrganicApplication)
   // fun getContentDao(): ContentDao
}