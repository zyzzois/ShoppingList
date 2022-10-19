package com.octaneocatane.shoppinglist.di

import com.octaneocatane.shoppinglist.presentation.MainActivity
import dagger.Component

@Component(modules = [DomainModule::class, ContextModule::class, BindingModule::class])
interface ApplicationComponent {
    fun inject(activity: MainActivity)
}