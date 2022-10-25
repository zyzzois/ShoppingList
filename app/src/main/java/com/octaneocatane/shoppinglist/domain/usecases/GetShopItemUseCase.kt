package com.octaneocatane.shoppinglist.domain.usecases

import com.octaneocatane.shoppinglist.domain.ShopItemEntity
import com.octaneocatane.shoppinglist.domain.ShopListRepository

class GetShopItemUseCase(private val shopListRepository: ShopListRepository) {
    suspend operator fun invoke(shopItemId: Int): ShopItemEntity {
        return shopListRepository.getShopItem(shopItemId)
    }
}