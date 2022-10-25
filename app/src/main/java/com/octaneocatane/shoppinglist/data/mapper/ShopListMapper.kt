package com.octaneocatane.shoppinglist.data.mapper

import com.octaneocatane.shoppinglist.data.database.ShopItemDbModel
import com.octaneocatane.shoppinglist.domain.ShopItemEntity

class ShopListMapper {
    fun mapEntityToDbModel(shopItemEntity: ShopItemEntity) = ShopItemDbModel(
        id = shopItemEntity.id,
        name = shopItemEntity.name,
        count = shopItemEntity.count,
        enabled = shopItemEntity.enabled
    )

    fun mapDbModelToEntity(shopItemDbModel: ShopItemDbModel) = ShopItemEntity(
        name = shopItemDbModel.name,
        count = shopItemDbModel.count,
        enabled = shopItemDbModel.enabled,
        id = shopItemDbModel.id
    )

    fun mapListDbModelToList(list: List<ShopItemDbModel>) = list.map {
        mapDbModelToEntity(it)
    }
}