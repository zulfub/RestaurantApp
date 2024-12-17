package com.huawei.hcase.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.huawei.hcase.entity.Dishes
import com.huawei.hcase.entity.cart
import com.huawei.hcase.repo.DishRepository
import com.huawei.hcase.repo.cartrepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CartView : ViewModel() {
    private val _cartList = MutableStateFlow<List<cart>>(emptyList())
    // External access as immutable
    val cartList: StateFlow<List<cart>> = _cartList

    var cartRepo = cartrepository()
    init {
        loadCart()
    }

    fun loadCart() {
        //Load dishes from repo
        viewModelScope.launch {
            val initalcartDishes = cartRepo.loadInitialDishes()
            _cartList.value = initalcartDishes
        }
    }
    fun addOrder(dishId: Int , dish_name:String,dish_img:String,dish_price:Int) {

        cart(
            dishId,
            dish_name,
            dish_img,
            dish_price
        )
        //Launch used for standart secure usage
        viewModelScope.launch {
            _cartList.value = cartList.value.map {
                if (it.dish_id == dishId) {
                    //Already in order
                    it
                } else {
                    it
                }
            }
        }
    }
}