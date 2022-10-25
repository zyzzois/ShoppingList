package com.octaneocatane.shoppinglist.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.octaneocatane.shoppinglist.data.repository.ShopListRepositoryImpl
import com.octaneocatane.shoppinglist.domain.usecases.DeleteShopItemUseCase
import com.octaneocatane.shoppinglist.domain.usecases.EditShopItemUseCase
import com.octaneocatane.shoppinglist.domain.usecases.GetShopListUseCase
import com.octaneocatane.shoppinglist.domain.ShopItemEntity
import kotlinx.coroutines.launch

class MainViewModel(application: Application): AndroidViewModel(application) {
    private val repository = ShopListRepositoryImpl(application)

    private val getShopListUseCase = GetShopListUseCase(repository)
    private val editShopItemUseCase = EditShopItemUseCase(repository)
    private val deleteShopItemUseCase = DeleteShopItemUseCase(repository)

    val shopList = getShopListUseCase()

    fun deleteShopItem(shopItemEntity: ShopItemEntity) {
        viewModelScope.launch {
            deleteShopItemUseCase(shopItemEntity)
        }
    }

    fun changeEnableState(shopItemEntity: ShopItemEntity) {
        val newItem = shopItemEntity.copy(enabled = !shopItemEntity.enabled)
        viewModelScope.launch {
            editShopItemUseCase(newItem)
        }
    }
}