package com.octaneocatane.data.mapper

import com.octaneocatane.data.database.ShopItemDbModel
import com.octaneocatane.domain.entity.ShopItemEntity
import javax.inject.Inject

class ShopListMapper @Inject constructor() {
    fun mapEntityToDbModel(shopItemEntity: ShopItemEntity) = ShopItemDbModel(
        id = shopItemEntity.id,
        name = shopItemEntity.name,
        count = shopItemEntity.count,
        enabled = shopItemEntity.enabled
    )

    fun mapDbModelToEntity(shopItemDbModel: ShopItemDbModel) =
        ShopItemEntity(
            name = shopItemDbModel.name,
            count = shopItemDbModel.count,
            enabled = shopItemDbModel.enabled,
            id = shopItemDbModel.id
        )

    fun mapListDbModelToList(list: List<ShopItemDbModel>) = list.map {
        mapDbModelToEntity(it)
    }
}