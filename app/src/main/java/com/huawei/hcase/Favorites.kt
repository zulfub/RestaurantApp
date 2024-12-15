package com.huawei.hcase

import android.annotation.SuppressLint
import android.app.Activity
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun Favorites(navController: NavController, viewModel: DishesView = viewModel()) {
    //Filter the favorite dishes which are favorite
    val dishesList by viewModel.dishesList.collectAsState()
    val favoriteDishesList = dishesList.filter { it.dish_favorite }
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Favorites") },
                //actions margin to right side
                actions = {
                    //Icon for returning to main page
                    Icon(
                        painter = painterResource(id = R.drawable.main_page),
                        contentDescription = "Home",
                        tint = Color.White,
                        modifier = Modifier
                            .padding(horizontal = 5.dp)
                            .clickable { navController.navigate("MainMenu") }
                    )
                },
                //Top Bar color configurations
                colors = TopAppBarDefaults.topAppBarColors(
                    //Background Color - From resources file -> R(res) , color(element), name
                    containerColor = colorResource(id = R.color.mainColorDark),
                    //Nav Content Color - From main library
                    titleContentColor = Color.White
                )


            )
        },
        content = {
            //Lazy column for products
            LazyColumn(modifier = Modifier.padding(top = 68.dp)) {
                items(
                    //How many items should shown
                    count = favoriteDishesList.size,
                    itemContent = {
                        //iterate list elements
                        val favoriteDish = favoriteDishesList[it]
                        //Dish card design
                        Card(
                            modifier = Modifier
                                .padding(all = 5.dp)
                                .fillMaxWidth()
                        ) {
                            //Row created for better click performance on Card
                            Row {
                                //Column Centered , and width fixed
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    modifier = Modifier
                                        .padding(all = 10.dp)
                                        .fillMaxWidth()
                                ) {
                                    //Taking img from res
                                    val activity = (LocalContext.current as Activity)
                                    Image(
                                        bitmap = ImageBitmap.imageResource(
                                            id = activity.resources.getIdentifier(
                                                favoriteDish.dish_img,
                                                "drawable",
                                                activity.packageName
                                            )
                                        ),
                                        contentDescription = "Product Details",
                                        modifier = Modifier.size(70.dp)
                                    )
                                    Row(
                                        verticalAlignment = Alignment.CenterVertically,
                                        modifier = Modifier.fillMaxWidth()
                                    ) {
                                        Column(
                                            verticalArrangement = Arrangement.SpaceEvenly,
                                            modifier = Modifier.fillMaxWidth()
                                        ) {
                                            Column {
                                                Text(
                                                    text = favoriteDish.dish_name,
                                                    fontSize = 20.sp,
                                                    color = Color.DarkGray
                                                )
                                                Text(
                                                    text = favoriteDish.dish_desc,
                                                    fontSize = 14.sp,
                                                    color = Color.Gray
                                                )
                                                Text(
                                                    text = "${favoriteDish.dish_price} $",
                                                    color = Color.Black
                                                )
                                            }
                                        }
                                    }
                                }
                            }
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.Center,
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                //Remove favorite Button
                                Icon(
                                    painter = painterResource(id = R.drawable.remove),
                                    contentDescription = "",
                                    modifier = Modifier
                                        .size(30.dp)
                                        .clickable {
                                            viewModel.toggleFavorite(favoriteDish.dish_id)
                                        }
                                )
                                Button(
                                    onClick = {
                                        //Ordering Method called
                                        //orderFood(dish0)
                                    },
                                    modifier = Modifier.padding(8.dp)
                                ) {
                                    Text(text = "Order")
                                }
                            }
                        }
                    }
                )
            }

        }
    )
}
