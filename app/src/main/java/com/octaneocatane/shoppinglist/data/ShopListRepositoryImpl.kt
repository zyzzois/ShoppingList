package com.octaneocatane.shoppinglist.data

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.Transformations
import com.octaneocatane.shoppinglist.domain.ShopItem
import com.octaneocatane.shoppinglist.domain.ShopListRepository
import java.lang.RuntimeException

class ShopListRepositoryImpl(application: Application): ShopListRepository {

    private val shopListDao = AppDataBase.getInstance(application).shopListDao()

    private val mapper = ShopListMapper()

    override suspend fun addShopItem(shopItem: ShopItem) {
        shopListDao.addShopItem(mapper.mapEntityToDbModel(shopItem))
    }

    override suspend fun deleteShopItem(shopItem: ShopItem) {
        shopListDao.deleteShopItem(shopItem.id)
    }

    override suspend fun editShopItem(shopItem: ShopItem) {
        shopListDao.addShopItem(mapper.mapEntityToDbModel(shopItem))
    }

    override suspend fun getShopItem(shopItemId: Int): ShopItem {
        val dbModel = shopListDao.getShopItem(shopItemId)
        return mapper.mapDbModelToEntity(dbModel)
    }

    override fun getShopList(): LiveData<List<ShopItem>> = Transformations.map(
        shopListDao.getShopList()
    ) {
        mapper.mapListDbModelToList(it)
    }

}