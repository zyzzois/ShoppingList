package com.octaneocatane.shoppinglist.domain.usecases

import androidx.lifecycle.LiveData
import com.octaneocatane.shoppinglist.domain.ShopItemEntity
import com.octaneocatane.shoppinglist.domain.ShopListRepository

class GetShopListUseCase(private val shopListRepository: ShopListRepository) {
    operator fun invoke(): LiveData<List<ShopItemEntity>> {
        return shopListRepository.getShopList()
    }
}