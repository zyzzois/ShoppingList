package com.octaneocatane.shoppinglist.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import com.octaneocatane.shoppinglist.R
import com.octaneocatane.shoppinglist.domain.ShopItem

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    private var count = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val item = ShopItem("name 0", 0, true, 0)
        setContentView(R.layout.activity_main)

        //проинициализируем нашу модель
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]

        //подпишемся на обновления
        viewModel.shopList.observe(this) {

            Log.d("MyLog", it.toString())
            if (count == 0) {
                val item = it[0]
                ++count
                viewModel.deleteShopItem(item)
            }

        }

    }
}