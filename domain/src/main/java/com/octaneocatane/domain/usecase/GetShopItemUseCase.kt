package com.octaneocatane.domain.usecase

import com.octaneocatane.domain.entity.ShopItemEntity
import com.octaneocatane.domain.repository.ShopListRepository
import javax.inject.Inject

class GetShopItemUseCase @Inject constructor(private val shopListRepository: ShopListRepository) {
    suspend operator fun invoke(shopItemId: Int): ShopItemEntity {
        return shopListRepository.getShopItem(shopItemId)
    }
}