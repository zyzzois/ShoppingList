package com.octaneocatane.shoppinglist.di

import androidx.lifecycle.ViewModel
import com.octaneocatane.shoppinglist.presentation.viewmodel.MainViewModel
import com.octaneocatane.shoppinglist.presentation.viewmodel.ShopItemViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelModule {

    @IntoMap
    @Binds
    @ViewModelKey(MainViewModel::class)
    fun bindMainViewModel(viewModel: MainViewModel): ViewModel

    @IntoMap
    @Binds
    @ViewModelKey(ShopItemViewModel::class)
    fun bindShopItemViewModel(viewModel: ShopItemViewModel): ViewModel

}