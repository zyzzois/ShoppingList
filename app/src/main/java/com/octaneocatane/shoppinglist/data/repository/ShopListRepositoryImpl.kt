package com.octaneocatane.shoppinglist.data.repository

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.octaneocatane.shoppinglist.data.database.AppDataBase
import com.octaneocatane.shoppinglist.data.mapper.ShopListMapper
import com.octaneocatane.shoppinglist.domain.ShopItemEntity
import com.octaneocatane.shoppinglist.domain.ShopListRepository

class ShopListRepositoryImpl(application: Application): ShopListRepository {

    private val shopListDao = AppDataBase.getInstance(application).shopListDao()

    private val mapper = ShopListMapper()

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