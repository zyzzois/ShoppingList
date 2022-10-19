package com.octaneocatane.shoppinglist.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.octaneocatane.shoppinglist.R
import com.octaneocatane.shoppinglist.databinding.ItemDisabledBinding
import com.octaneocatane.shoppinglist.databinding.ItemEnabledBinding
import com.octaneocatane.shoppinglist.domain.ShopItem
import javax.inject.Inject

class ShopListAdapter: androidx.recyclerview.widget.ListAdapter<ShopItem, ShopItemViewHolder>(ShopItemDiffCallback()) {

    var onShopItemLongClickListener: ((ShopItem) -> Unit)? = null
    var onShopItemClickListener: ((ShopItem) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShopItemViewHolder {

        val layout = when(viewType) {
            VIEW_TYPE_DISABLED -> R.layout.item_disabled
            VIEW_TYPE_ENABLED  -> R.layout.item_enabled
            else -> throw RuntimeException("Unknown view type: $viewType")
        }
        val binding = DataBindingUtil.inflate<ViewDataBinding>(
            LayoutInflater.from(parent.context),
            layout,
            parent,
            false
        )
        return ShopItemViewHolder(binding)
    }

    override fun onBindViewHolder(viewHolder: ShopItemViewHolder, position: Int) {
        val shopItem = getItem(position)
        val binding = viewHolder.binding

        binding.root.setOnLongClickListener {
            onShopItemLongClickListener?.invoke(shopItem)
            true
        }

        binding.root.setOnClickListener {
            onShopItemClickListener?.invoke(shopItem)
        }
        when (binding) {
            is ItemDisabledBinding -> {
                binding.tvName.text = shopItem.name
                binding.tvCount.text = shopItem.count.toString()
            }
            is ItemEnabledBinding -> {
                binding.tvName.text = shopItem.name
                binding.tvCount.text = shopItem.count.toString()
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (getItem(position).enabled) VIEW_TYPE_ENABLED else VIEW_TYPE_DISABLED
    }

    companion object {
        const val VIEW_TYPE_DISABLED = 0
        const val VIEW_TYPE_ENABLED = 1
        const val MAX_POOL_SIZE = 15
    }
}