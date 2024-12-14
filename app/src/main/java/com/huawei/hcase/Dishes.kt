package com.huawei.hcase

data class Dishes(
    var dish_id: Int,
    var dish_name: String,
    var dish_desc: String,
    var dish_img: String,
    var dish_price: Int,
    var dish_favorite: Boolean
) {
//data class for DB operations
}