package com.octaneocatane.shoppinglist.domain.usecases

import com.octaneocatane.shoppinglist.domain.ShopItemEntity
import com.octaneocatane.shoppinglist.domain.ShopListRepository

class AddShopItemUseCase(private val shopListRepository: ShopListRepository) {
    suspend operator fun invoke(shopItemEntity: ShopItemEntity) {
        shopListRepository.addShopItem(shopItemEntity)
    }
}