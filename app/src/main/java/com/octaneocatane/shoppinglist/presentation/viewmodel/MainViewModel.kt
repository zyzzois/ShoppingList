package com.octaneocatane.shoppinglist.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.octaneocatane.domain.entity.ShopItemEntity
import com.octaneocatane.domain.usecase.DeleteShopItemUseCase
import com.octaneocatane.domain.usecase.EditShopItemUseCase
import com.octaneocatane.domain.usecase.GetShopListUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val editShopItemUseCase: EditShopItemUseCase,
    private val deleteShopItemUseCase: DeleteShopItemUseCase,
    private val getShopListUseCase: GetShopListUseCase
): ViewModel() {

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