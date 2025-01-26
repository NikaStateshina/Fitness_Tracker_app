package com.example.fitness_tracker

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.material.Card
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.sp
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension


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

    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFf0f0f0))
    ) {
        val (tabRowTop, content, goButton, tabRowBottom) = createRefs()
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
            modifier = Modifier.height(64.dp).constrainAs(tabRowTop) {
                top.linkTo(parent.top)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }
        ) {
            tabTitles.forEachIndexed { index, title ->
                Tab(
                    selected = selectedTopTab == index,
                    onClick = { selectedTopTab = index },
                    text = {
                        Text(
                            text = title,
                            color = if (selectedTopTab == index) Color(0xFF4B09F3) else Color(0xFF808080),
                        )
                    }
                )
            }
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .constrainAs(content) {
                    top.linkTo(tabRowTop.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    bottom.linkTo(goButton.top)
                    height = Dimension.fillToConstraints
                }
        ) {
            when (selectedTopTab) {
                0 -> VerticalListForMyItems(navController)
                1 -> VerticalListForUserItems(navController)
            }
        }
        Image(
            painter = painterResource(id = R.drawable.gobutton),
            contentDescription = null,
            modifier = Modifier
                .size(80.dp)
                .clip(CircleShape)
                .clickable {
                    navController.navigate("GreetingPreview")
                }
                .constrainAs(goButton) {
                    bottom.linkTo(tabRowBottom.bottom, 90.dp)
                    end.linkTo(parent.end, 20.dp)
                },
            contentScale = ContentScale.Crop
        )
        TabRow(
            selectedTabIndex = selectedBottomTab,
            backgroundColor = Color.White,
            indicator = {},

            contentColor = Color.Black,
            modifier = Modifier.height(64.dp).constrainAs(tabRowBottom) {
                bottom.linkTo(parent.bottom, 10.dp)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }
        ) {
            tabIcons.forEachIndexed { index, iconRes ->
                Tab(
                    selected = selectedBottomTab == index,
                    onClick = { selectedBottomTab = index },
                    text = {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Image(
                                painter = painterResource(id = iconRes),
                                contentDescription = null,
                                modifier = Modifier.size(24.dp)

                            )
                            Text(
                                text = if (index == 0) stringResource(R.string.TabActivity) else stringResource(R.string.TabProfile),
                                color = if (selectedBottomTab == index) Color.Black else Color.Gray,
                                fontSize = 12.sp
                            )
                        }
                    }
                )
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
                        if (item == R.drawable.myvelo) {
                            navController.navigate("ActivityMyDetails")                         }
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
fun VerticalListForUserItems(navController: NavController) {
    val Yesterday = stringResource(R.string.Yesterday)
    val items = listOf(
        Yesterday,
        R.drawable.userserfing,
        R.drawable.userswing,
        R.drawable.usercadillac
    )
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
                                if (item == R.drawable.userserfing) {
                                    navController.navigate("ActivityUserDetails")                         }
                            },
                        shape = RoundedCornerShape(8.dp),
                        elevation = 4.dp
                    ) {
                        Image(
                            painter = painterResource(id = item),
                            contentDescription = null,
                            modifier = Modifier
                                .fillMaxWidth()
                        )
                    }
                }
            }
        }
    }
}
