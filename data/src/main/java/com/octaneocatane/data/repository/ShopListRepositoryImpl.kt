package com.octaneocatane.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.octaneocatane.data.database.ShopListDao
import com.octaneocatane.data.mapper.ShopListMapper
import com.octaneocatane.domain.entity.ShopItemEntity
import com.octaneocatane.domain.repository.ShopListRepository
import javax.inject.Inject

class ShopListRepositoryImpl @Inject constructor(
    private val shopListDao: ShopListDao,
    private val mapper: ShopListMapper
): ShopListRepository {

    override suspend fun addShopItem(shopItemEntity: ShopItemEntity) {
        shopListDao.addShopItem(mapper.mapEntityToDbModel(shopItemEntity))
    }

    override suspend fun deleteShopItem(shopItemEntity: ShopItemEntity) {
        shopListDao.deleteShopItem(shopItemEntity.id)
    }

    override suspend fun editShopItem(shopItemEntity: ShopItemEntity) {
        shopListDao.addShopItem(mapper.mapEntityToDbModel(shopItemEntity))
    }

    override suspend fun getShopItem(shopItemId: Int): ShopItemEntity {
        val dbModel = shopListDao.getShopItem(shopItemId)
        return mapper.mapDbModelToEntity(dbModel)
    }

    override fun getShopList(): LiveData<List<ShopItemEntity>> = Transformations.map(
        shopListDao.getShopList()
    ) {
        mapper.mapListDbModelToList(it)
    }

}