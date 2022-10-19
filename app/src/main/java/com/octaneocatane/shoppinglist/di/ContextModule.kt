package com.octaneocatane.shoppinglist.di

import android.app.Application
import dagger.Module
import dagger.Provides

@Module
class ContextModule(private val application: Application) {

    @Provides
    fun provideContext(application: Application): Application {
        return application
    }

}