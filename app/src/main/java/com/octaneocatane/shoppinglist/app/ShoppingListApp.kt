package com.octaneocatane.shoppinglist.app

import android.app.Application
import com.octaneocatane.shoppinglist.di.DaggerApplicationComponent

class ShoppingListApp: Application() {
    val component by lazy {
        DaggerApplicationComponent.factory().create(this)

    }
}