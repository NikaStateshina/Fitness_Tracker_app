package com.example.fitness_tracker

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Card
import androidx.compose.material.Scaffold
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.TabRowDefaults
import androidx.compose.material.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.sp
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip


@Composable
fun MyScreenWithBackground(navController: NavController) {
    val backgroundColor = Color(0xFFf0f0f0)
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
    ) {
        Activity(navController = navController)
    }
}

@Composable
fun Activity(navController: NavController) {
    var selectedTopTab by remember { mutableStateOf(0) }
    var selectedBottomTab by remember { mutableStateOf(0) }

    val tabTitles = listOf(
        stringResource(R.string.HeaderMyActivity),
        stringResource(R.string.HeaderUsersActivity)
    )
    val tabIcons = listOf(
        R.drawable.ic_sports_selected,
        R.drawable.ic_person_non_selected
    )

    Scaffold(
        topBar = {
            TabRow(
                selectedTabIndex = selectedTopTab,
                backgroundColor = Color.White,
                contentColor = Color.Black,
                indicator = { tabPositions ->
                    TabRowDefaults.Indicator(
                        color = Color(0xFF4B09F3),
                        height = 2.dp,
                        modifier = Modifier.tabIndicatorOffset(tabPositions[selectedTopTab])
                    )
                },
                modifier = Modifier.height(64.dp)
            ) {
                tabTitles.forEachIndexed { index, title ->
                    Tab(
                        selected = selectedTopTab == index,
                        onClick = { selectedTopTab = index },
                        text = {
                            Text(
                                text = title,
                                color = if (selectedTopTab == index) Color(0xFF4B09F3)
                                else Color(0xFF808080),
                            )
                        }
                    )
                }
            }
        },
        bottomBar = {
            BottomNavigation(
                backgroundColor = Color.White,
                contentColor = Color.Black,
                modifier = Modifier.height(64.dp)
            ) {
                tabIcons.forEachIndexed { index, iconRes ->
                    BottomNavigationItem(
                        selected = selectedBottomTab == index,
                        onClick = {
                            selectedBottomTab = index
                            if (index == 0) {
                                navController.navigate(Routes.Activity) {
                                    popUpTo(navController.graph.startDestinationId){
                                        saveState = true
                                    }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            } else if (index == 1) {
                                navController.navigate(Routes.ProfileScreen){
                                    popUpTo(navController.graph.startDestinationId){
                                        saveState = true
                                    }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            }
                        },
                        icon = {
                            Image(
                                painter = painterResource(id = iconRes),
                                contentDescription = null,
                                modifier = Modifier.size(24.dp)
                            )
                        },
                        label = {
                            Text(
                                text = if (index == 0) stringResource(R.string.TabActivity)
                                else stringResource(R.string.TabProfile),
                                color = if (selectedBottomTab == index) Color.Black
                                else Color.Gray,
                                fontSize = 12.sp
                            )
                        }
                    )
                }
            }
        },
        content = { innerPadding ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .background(Color(0xFFf0f0f0))
            ) {
                when (selectedTopTab) {
                    0 -> VerticalListForMyItems(navController)
                    1 -> VerticalListForUserItems(navController)
                }
                Image(
                    painter = painterResource(id = R.drawable.gobutton),
                    contentDescription = null,
                    modifier = Modifier
                        .size(180.dp)
                        .clip(CircleShape)
                        .clickable {
                            navController.navigate("NewActivity")
                        }
                        .align(Alignment.BottomEnd)
                        .padding(end = 16.dp, bottom = 90.dp)
                )
            }
        }
    )
}



@Composable
fun VerticalListForItems(items: List<Any>,
                         navController: NavController){
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(vertical = 8.dp)
    ) {
        items(items) { item ->
            when (item) {
                is String -> {
                    Text(
                        text = item,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 10.dp),
                        style = TextStyle(
                            fontFamily = FontFamily.SansSerif,
                            fontSize = 24.sp,
                            color = Color(0xFF808080)
                        )
                    )
                }
                is Int -> {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp)
                            .clickable {
                                if (item == R.drawable.myvelo){
                                    navController.navigate("ActivityMyDetails")
                                }
                                if (item == R.drawable.userserfing) {
                                    navController.navigate("ActivityUserDetails")
                                }
                                },

                        shape = RoundedCornerShape(8.dp),
                        elevation = 4.dp
                    ) {
                        Image(
                            painter = painterResource(id = item),
                            contentDescription = null,
                            modifier = Modifier
                                .fillMaxWidth(),
                        )
                    }
                }
            }
        }
    }

}

@Composable
fun VerticalListForMyItems(navController: NavController) {
    val Yesterday = stringResource(R.string.Yesterday)
    val May2022 = stringResource(R.string.May2022)
    val items = listOf(
        Yesterday,
        R.drawable.myserfing,
        May2022,
        R.drawable.myvelo
    )
    VerticalListForItems(items, navController)
}


@Composable
fun VerticalListForUserItems(navController: NavController) {
    val Yesterday = stringResource(R.string.Yesterday)
    val items = listOf(
        Yesterday,
        R.drawable.userserfing,
        R.drawable.userswing,
        R.drawable.usercadillac
    )
    VerticalListForItems(items, navController)
}
