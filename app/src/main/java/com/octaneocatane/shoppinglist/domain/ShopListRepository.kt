package com.octaneocatane.shoppinglist.domain

import androidx.lifecycle.LiveData

interface ShopListRepository {
    suspend fun addShopItem(shopItemEntity: ShopItemEntity)
    suspend fun deleteShopItem(shopItemEntity: ShopItemEntity)
    suspend fun editShopItem(shopItemEntity: ShopItemEntity)
    suspend fun getShopItem(shopItemId: Int): ShopItemEntity
    fun getShopList(): LiveData<List<ShopItemEntity>>
}