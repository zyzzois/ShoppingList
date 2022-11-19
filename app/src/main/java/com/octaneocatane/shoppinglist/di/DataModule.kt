package com.octaneocatane.shoppinglist.di

import android.app.Application
import com.octaneocatane.data.database.AppDataBase
import com.octaneocatane.data.database.ShopListDao
import com.octaneocatane.data.repository.ShopListRepositoryImpl
import com.octaneocatane.domain.repository.ShopListRepository
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
interface DataModule {

    @ApplicationScope
    @Binds
    fun bindShopListRepository(impl: ShopListRepositoryImpl): ShopListRepository

    companion object {

        @ApplicationScope
        @Provides
        fun provideShopListDao(
            application: Application
        ): ShopListDao {
            return AppDataBase.getInstance(application).shopListDao()
        }

    }

}