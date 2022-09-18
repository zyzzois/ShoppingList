package com.octaneocatane.shoppinglist.presentation

import androidx.lifecycle.ViewModel
import com.octaneocatane.shoppinglist.data.ShopListRepositoryImpl
import com.octaneocatane.shoppinglist.domain.DeleteShopItemUseCase
import com.octaneocatane.shoppinglist.domain.EditShopItemUseCase
import com.octaneocatane.shoppinglist.domain.GetShopListUseCase
import com.octaneocatane.shoppinglist.domain.ShopItem

class MainViewModel: ViewModel() {
    private val repository = ShopListRepositoryImpl

    private val getShopListUseCase = GetShopListUseCase(repository)
    private val editShopItemUseCase = EditShopItemUseCase(repository)
    private val deleteShopItemUseCase = DeleteShopItemUseCase(repository)

    val shopList = getShopListUseCase.getShopList()

    fun deleteShopItem(shopItem: ShopItem) {
        deleteShopItemUseCase.deleteShopItem(shopItem)
    }

    fun changeEnableState(shopItem: ShopItem) {
        val newItem = shopItem.copy(enabled = !shopItem.enabled)
        editShopItemUseCase.editShopItem(newItem)
    }
}