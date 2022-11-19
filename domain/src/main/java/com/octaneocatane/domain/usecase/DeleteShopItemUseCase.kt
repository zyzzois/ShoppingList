package com.octaneocatane.domain.usecase

import com.octaneocatane.domain.entity.ShopItemEntity
import com.octaneocatane.domain.repository.ShopListRepository
import javax.inject.Inject

class DeleteShopItemUseCase @Inject constructor(private val shopListRepository: ShopListRepository) {
    suspend operator fun invoke(shopItemEntity: ShopItemEntity) {
        shopListRepository.deleteShopItem(shopItemEntity)
    }
}