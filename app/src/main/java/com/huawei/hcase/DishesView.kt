package com.huawei.hcase

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DishesView : ViewModel() {
    //Mutable in Class
    private val _dishesList = MutableStateFlow<List<Dishes>>(emptyList())

    //Immutable when get() method called
    val dishesList: StateFlow<List<Dishes>> get() = _dishesList

    init {
        // Load initial dish
        loadDishes()
    }

    private fun loadDishes() {
        // Example of basic DB
        //Changes are not reflect for this
        val initialDishes = listOf(
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
        _dishesList.value = initialDishes
    }

    fun toggleFavorite(dishId: Int) {
        //Launch used for standart secure usage
        viewModelScope.launch {
            _dishesList.value = _dishesList.value.map {
                if (it.dish_id == dishId) {
                    //Data Class Immutable - creating copy of dish with different favorite value
                    it.copy(dish_favorite = !it.dish_favorite)
                } else {
                    it
                }
            }
        }
    }
}