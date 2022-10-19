package com.octaneocatane.shoppinglist.di

import androidx.databinding.ViewDataBinding
import dagger.Module
import dagger.Provides

@Module
class BindingModule(private val binding: ViewDataBinding) {
    @Provides
    fun provideBinding(binding: ViewDataBinding): ViewDataBinding {
        return binding
    }
}