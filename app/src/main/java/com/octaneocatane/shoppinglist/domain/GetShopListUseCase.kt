package com.octaneocatane.shoppinglist.domain

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProvider
import com.octaneocatane.shoppinglist.presentation.MainActivity
import com.octaneocatane.shoppinglist.presentation.MainViewModel
import javax.inject.Inject

class GetShopListUseCase @Inject constructor(private val shopListRepository: ShopListRepository) {
    fun getShopList(): LiveData<List<ShopItem>> {
        return shopListRepository.getShopList()
    }
}