package com.octaneocatane.shoppinglist.di

import android.app.Application
import com.octaneocatane.shoppinglist.presentation.screens.MainActivity
import com.octaneocatane.shoppinglist.presentation.screens.ShopItemActivity
import com.octaneocatane.shoppinglist.presentation.screens.ShopItemFragment
import dagger.BindsInstance
import dagger.Component

@ApplicationScope
@Component(
    modules = [
        DataModule::class,
        ViewModelModule::class
    ]
)
interface ApplicationComponent {

    fun inject(activity: MainActivity)
    fun inject(fragment: ShopItemFragment)

    @Component.Factory
    interface ApplicationComponentFactory {
        fun create(
            @BindsInstance application: Application
        ): ApplicationComponent
    }

}