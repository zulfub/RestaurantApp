package com.huawei.hcase.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.huawei.hcase.entity.Dishes
import com.huawei.hcase.repo.DishRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DishesView : ViewModel() {

    private val _dishesList = MutableStateFlow<List<Dishes>>(emptyList())

    // External access as immutable
    val dishesList: StateFlow<List<Dishes>> = _dishesList

    var dishRepo = DishRepository()

    init {
        // Load initial dish
        loadDishes()
    }

    fun loadDishes() {
        //Load dishes from repo
        viewModelScope.launch {
            val initialDishes = dishRepo.loadInitialDishes()
            _dishesList.value = initialDishes
        }
    }


    fun toggleFavorite(dishId: Int) {
        //Launch used for standart secure usage
        viewModelScope.launch {
            _dishesList.value = dishesList.value.map {
                if (it.dish_id == dishId) {
                    //Data Class Immutable - creating copy of dish with different favorite value
                    it.copy(dish_favorite = !it.dish_favorite)
                } else {
                    it
                }
            }
        }
    }
    fun toggleOrder(dishId: Int){
        viewModelScope.launch {
            _dishesList.value = dishesList.value.map {
                if (it.dish_id == dishId) {
                    //Data Class Immutable - creating copy of dish with different favorite value
                    it.copy(dish_addedCard = !it.dish_addedCard)
                } else {
                    it
                }
            }
        }
    }
}