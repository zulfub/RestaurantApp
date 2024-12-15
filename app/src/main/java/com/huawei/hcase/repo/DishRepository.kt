package com.huawei.hcase.repo

import com.huawei.hcase.entity.Dishes
import kotlinx.coroutines.flow.MutableStateFlow

//DB operations
class DishRepository {
    private val initialDishesList = listOf(
            Dishes(
                1,
                "Pasta",
                "Italian pasta with tomato sauce and meatballs",
                "pasta_img",
                480,
                true
            ),
            Dishes(
                2,
                "Salad",
                "Fresh and organic picked vegetables with tomatoes",
                "salad_img",
                350,
                false
            ),
            Dishes(
                3,
                "Nachos",
                "Traditional Mexican dish with beans and beef",
                "nachos_img",
                450,
                true
            )
        )

    // Returns the initial dishes
    fun loadInitialDishes(): List<Dishes> {
        return initialDishesList
    }
}