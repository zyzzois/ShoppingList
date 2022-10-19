package com.octaneocatane.shoppinglist.di

import com.octaneocatane.shoppinglist.data.ShopListRepositoryImpl
import com.octaneocatane.shoppinglist.domain.ShopListRepository
import dagger.Binds
import dagger.Module

@Module
interface DomainModule {
    @Binds
    fun bindShopListRepository(impl: ShopListRepositoryImpl): ShopListRepository

}