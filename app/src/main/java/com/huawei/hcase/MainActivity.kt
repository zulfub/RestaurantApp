package com.huawei.hcase

import android.annotation.SuppressLint
import android.app.Activity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
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
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.huawei.hcase.ui.theme.HcaseTheme
import androidx.lifecycle.viewmodel.compose.viewModel
import com.huawei.hcase.viewmodel.DishesView

//var favorite: Int = R.drawable.unfavorited

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Navigation between pages
        setContent {
            HcaseTheme {
                AppNavigation()
            }
        }
    }
}

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    //creating viewModel for initial load
    val viewModel: DishesView = viewModel()
    NavHost(navController = navController, startDestination = "MainMenu") {
        composable("MainMenu") {
            MainMenu(navController = navController, viewModel)
        }
        composable("Favorites")
        {
            //Favorites(dishesList)
            Favorites(navController = navController, viewModel)
        }
        composable("Cart")
        {
            //Favorites(dishesList)
            Cart(navController = navController, viewModel)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainMenu(navController: NavController, viewModel: DishesView = viewModel()) {
    //Getting list from viewModel with loaded before do not load again
    val dishesList by viewModel.dishesList.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Menu") },
                //actions margin to right side
                actions = {
                    Row{ Icon(
                        //For favorites page
                        painter = painterResource(id = R.drawable.cart_img),
                        contentDescription = "Cart Page",
                        tint = Color.White,
                        //Click function for navigation to favorites page
                        modifier = Modifier
                            .padding(horizontal = 5.dp)
                            .clickable { navController.navigate("Cart") }
                    )
                    Icon(
                        //For favorites page
                        painter = painterResource(id = R.drawable.favorite_page),
                        contentDescription = "Favorite Page",
                        tint = Color.White,
                        //Click function for navigation to favorites page
                        modifier = Modifier
                            .padding(horizontal = 5.dp)
                            .clickable { navController.navigate("Favorites") }
                    )}
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
                    count = dishesList.size,
                    itemContent = {
                        //iterate list elements
                        val dish = dishesList[it]
                        //Dish card design
                        Card(
                            modifier = Modifier
                                .padding(all = 5.dp)
                                .fillMaxWidth()
                        ) {
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
                                            dish.dish_img, "drawable", activity.packageName
                                        )
                                    ),
                                    contentDescription = "Product Details",
                                    modifier = Modifier.size(120.dp)
                                )
                                Column(
                                    verticalArrangement = Arrangement.SpaceEvenly,
                                    modifier = Modifier.fillMaxWidth()
                                ) {
                                    Column {
                                        Text(
                                            text = dish.dish_name,
                                            fontSize = 20.sp,
                                            color = Color.DarkGray
                                        )
                                        Text(
                                            text = dish.dish_desc,
                                            fontSize = 14.sp,
                                            color = Color.Gray
                                        )
                                        Text(
                                            text = "${dish.dish_price} $",
                                            color = Color.Black
                                        )
                                    }
                                }

                            }
                            Row(
                                horizontalArrangement = Arrangement.Center,
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                //Favorite Button
                                Icon(
                                    painter = painterResource(id = if (dish.dish_favorite) R.drawable.favorited else R.drawable.unfavorited),
                                    contentDescription = "",
                                    modifier = Modifier
                                        .size(30.dp)
                                        .clickable {
                                            viewModel.toggleFavorite(dish.dish_id)
                                        }
                                )
                                if (dish.dish_addedCard){
                                    Icon(
                                        painter = painterResource(id = R.drawable.remove_cart),
                                        contentDescription = "",
                                        modifier = Modifier
                                            .size(30.dp)
                                            .clickable {
                                                viewModel.toggleOrder(dish.dish_id)
                                            }
                                    )}
                                    else {
                                    Button(
                                        onClick = {
                                            //Ordering Method called
                                            viewModel.toggleOrder(dish.dish_id)
                                        },
                                        modifier = Modifier.padding(8.dp),
                                        colors = ButtonDefaults.buttonColors(
                                            containerColor = Color.DarkGray
                                        )

                                    ) {
                                        Text(text = "Order")
                                    }
                                    }

                            }
                        }
                    }
                )
            }

        }
    )
}

@Preview(showBackground = true)
@Composable
fun MainMenuPreview() {
    HcaseTheme {
    }
}