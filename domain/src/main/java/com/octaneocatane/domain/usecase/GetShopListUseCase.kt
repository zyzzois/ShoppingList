package com.octaneocatane.domain.usecase

import androidx.lifecycle.LiveData
import com.octaneocatane.domain.entity.ShopItemEntity
import com.octaneocatane.domain.repository.ShopListRepository
import javax.inject.Inject

class GetShopListUseCase @Inject constructor(private val shopListRepository: ShopListRepository) {
    operator fun invoke(): LiveData<List<ShopItemEntity>> {
        return shopListRepository.getShopList()
    }
}