package com.octaneocatane.shoppinglist.presentation.recycler

import androidx.recyclerview.widget.DiffUtil
import com.octaneocatane.domain.entity.ShopItemEntity

class ShopItemDiffCallback: DiffUtil.ItemCallback<ShopItemEntity>() {
    override fun areItemsTheSame(oldItem: ShopItemEntity, newItem: ShopItemEntity): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: ShopItemEntity, newItem: ShopItemEntity): Boolean {
        return oldItem == newItem
    }
}