package com.octaneocatane.shoppinglist.domain

data class ShopItem(
    val name: String,
    val count: Int,
    val id: Int,
    val enabled: Boolean
)
