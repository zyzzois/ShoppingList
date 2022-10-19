package com.octaneocatane.shoppinglist.presentation

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.octaneocatane.shoppinglist.databinding.ItemDisabledBinding
import javax.inject.Inject

class ShopItemViewHolder @Inject constructor(val binding: ViewDataBinding):
    RecyclerView.ViewHolder(binding.root)