
package com.huawei.hcase.entity

data class Dishes(
    var dish_id: Int,
    var dish_name: String,
    var dish_desc: String,
    var dish_img: String,
    var dish_price: Int,
    var dish_favorite: Boolean,
    var dish_addedCard:Boolean
) {
//data class for DB operations
}