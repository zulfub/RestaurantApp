package com.huawei.hcase.repo

import com.huawei.hcase.entity.Dishes

//Featured DB operations
class DishRepository {
    private val initialDishesList = listOf(
        Dishes(
            1,
            "Pasta",
            "Italian pasta with tomato sauce and meatballs",
            "pasta_img",
            80,
            true,
            false
        ),
        Dishes(
            2,
            "Salad",
            "Fresh and organic picked vegetables with tomatoes",
            "salad_img",
            60,
            false,
            false
        ),
        Dishes(
            3,
            "Nachos",
            "Traditional Mexican dish with beans and beef",
            "nachos_img",
            150,
            true,
            false
        ),
        Dishes(
            4,
            "SteakHouse",
            "Medium Rare Kobe Beef with vegatables on side",
            "steak_img",
            1000,
            true,
            false
        )
    )

    // Returns the initial dishes
    fun loadInitialDishes(): List<Dishes> {
        return initialDishesList
    }
}