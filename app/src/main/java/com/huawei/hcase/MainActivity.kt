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
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
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
import androidx.navigation.NavHost
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.huawei.hcase.ui.theme.HcaseTheme
import androidx.lifecycle.viewmodel.compose.viewModel

//var favorite: Int = R.drawable.unfavorited

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()
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
    NavHost(navController = navController, startDestination = "MainMenu") {
        composable("MainMenu") {
            MainMenu(navController = navController, viewModel = viewModel())
        }
        composable("Favorites")
        {
            //Favorites(dishesList)
            Favorites(navController = navController, viewModel = viewModel())
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainMenu(navController: NavController, viewModel: DishesView = viewModel()) {
    //Taking dish list from view Dish list
    val dishesList by viewModel.dishesList.collectAsState()
    //App launched list should fill MainMenu page
    /* LaunchedEffect(key1 = true) {
         //later will take from db
         val dish1 =
             Dishes(
                 1,
                 "Pasta",
                 "Italian pasta with tomato sauce and meatballs",
                 "pasta_img",
                 480,
                 true
             )
         val dish2 = Dishes(
             2,
             "Salad",
             "Fresh and organic picked vegetables with tomatoes ",
             "salad_img",
             350,
             false
         )

         val dish3 = Dishes(
             3,
             "Nachos",
             "Traditional Mexican dish with mexican beans and beef",
             "nachos_img",
             450,
             false
         )

         dishesList.add(dish1)
         dishesList.add(dish2)
         dishesList.add(dish3)
     }*/

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Dishes") },
                //actions margin to right side
                actions = {
                    Icon(
                        //For favorites page
                        painter = painterResource(id = R.drawable.favorite_page),
                        contentDescription = "Favorite Page",
                        tint = Color.White,
                        //Click function for navigation to favorites page
                        modifier = Modifier
                            .padding(horizontal = 5.dp)
                            .clickable { navController.navigate("Favorites") }
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
                                                dish.dish_img, "drawable", activity.packageName
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
                                }
                            }
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.Center,
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                //Favorite Button
                                Icon(
                                    painter = painterResource(id = if (dish.dish_favorite) R.drawable.favorited else R.drawable.unfavorited),
                                    contentDescription = "",
                                    modifier = Modifier
                                        .size(20.dp)
                                        .clickable {
                                            viewModel.toggleFavorite(dish.dish_id)
                                        }
                                )
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