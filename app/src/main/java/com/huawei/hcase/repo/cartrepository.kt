package com.huawei.hcase.repo

import com.huawei.hcase.entity.Dishes
import com.huawei.hcase.entity.cart

class cartrepository {
    private val initialCartList = emptyList<cart>()

    fun loadInitialDishes(): List<cart> {
        return initialCartList
    }
}