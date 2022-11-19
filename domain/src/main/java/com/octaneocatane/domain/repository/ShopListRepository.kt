package com.octaneocatane.domain.repository

import androidx.lifecycle.LiveData
import com.octaneocatane.domain.entity.ShopItemEntity

interface ShopListRepository {
    suspend fun addShopItem(shopItemEntity: ShopItemEntity)
    suspend fun deleteShopItem(shopItemEntity: ShopItemEntity)
    suspend fun editShopItem(shopItemEntity: ShopItemEntity)
    suspend fun getShopItem(shopItemId: Int): ShopItemEntity
    fun getShopList(): LiveData<List<ShopItemEntity>>
}