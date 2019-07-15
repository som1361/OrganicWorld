package com.example.organicworld.application

import android.app.Application
import com.example.organicworld.DI.component.AppComponent
import com.example.organicworld.DI.component.AsyncComponent
import com.example.organicworld.DI.component.DaggerAppComponent
import com.example.organicworld.DI.component.DaggerAsyncComponent
import com.example.organicworld.DI.module.AppModule


class OrganicApplication : Application() {

    lateinit var organicComponent: AppComponent
    companion object {
        private lateinit var  asyncComponent: AsyncComponent
        fun getAsyncComponent(): AsyncComponent{
            return asyncComponent
        }
    }

    private fun initDagger(app: OrganicApplication): AppComponent =
        DaggerAppComponent.builder()
            .appModule(AppModule(app))
            .build()

    override fun onCreate() {
        super.onCreate()
        organicComponent = initDagger(this)
        asyncComponent = DaggerAsyncComponent.create()
    }
}