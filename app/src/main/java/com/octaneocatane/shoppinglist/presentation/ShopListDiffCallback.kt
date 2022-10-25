package com.octaneocatane.shoppinglist.presentation

import androidx.recyclerview.widget.DiffUtil
import com.octaneocatane.shoppinglist.domain.ShopItemEntity

class ShopListDiffCallback(
    private val oldList: List<ShopItemEntity>,
    private val newList: List<ShopItemEntity>,
): DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }

}

