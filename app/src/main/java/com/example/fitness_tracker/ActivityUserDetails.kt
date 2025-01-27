package com.example.fitness_tracker

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Scaffold
import androidx.compose.material.TopAppBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.fitness_tracker.ui.theme.ArrowBackButton
import com.example.fitness_tracker.ui.theme.TextHeaderStyle

@Composable
fun ActivityUserDetails(navController: NavController) {
    val tabIcons = listOf(
        R.drawable.ic_sports_selected,
        R.drawable.ic_person_non_selected
    )

    var selectedBottomTab by rememberSaveable { mutableStateOf(0) }

    Scaffold(
        topBar = {
            TopAppBar(
                backgroundColor = Color.White,
                elevation = 0.dp,
                modifier = Modifier.padding(top = 16.dp),
                title = {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        ArrowBackButton(navController, Routes.Activity)
                        Spacer(modifier = Modifier.width(16.dp))
                        Text(
                            text = "Серфинг",
                            style = TextHeaderStyle
                        )
                        Spacer(modifier = Modifier.weight(1f))
                        Image(
                            painter = painterResource(id = R.drawable.ic_delete),
                            contentDescription = "Удалить"
                        )
                        Spacer(modifier = Modifier.width(16.dp))
                        Image(
                            painter = painterResource(id = R.drawable.ic_share),
                            contentDescription = "Поделиться"
                        )
                    }
                }
            )
        },
        bottomBar = {
            BottomNavigation(
                backgroundColor = Color.White,
                contentColor = Color.Black,
                modifier = Modifier.height(90.dp)
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
                                navController.navigate(Routes.ProfileScreen) {
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
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(start = 20.dp)
        ) {
            Text(
                text = "@Van_van",
                style = TextStyle(
                    fontFamily = FontFamily.SansSerif,
                    fontSize = 16.sp,
                    color = Color(0xFF4B09F3),
                    textAlign = TextAlign.Start
                )
            )
            Text(
                modifier = Modifier.padding(top = 5.dp),
                text = "14.32 км",
                style = TextHeaderStyle
            )
            Text(
                modifier = Modifier.padding(top = 5.dp),
                text = "14 часов назад",
                style = TextStyle(
                    fontFamily = FontFamily.SansSerif,
                    fontSize = 16.sp,
                    color = Color(0xFF808080),
                    textAlign = TextAlign.Start
                )
            )
            Text(
                modifier = Modifier.padding(top = 30.dp),
                text = "1 ч 42 мин",
                style = TextHeaderStyle
            )
            Image(
                painter = painterResource(id = R.drawable.timeline),
                contentDescription = "Время",
                modifier = Modifier.padding(top = 10.dp)
            )
            Spacer(modifier = Modifier.height(40.dp))
            Image(
                painter = painterResource(id = R.drawable.comment),
                contentDescription = "Комментарий",
                modifier = Modifier.padding(bottom = 16.dp)
            )
        }
    }
}
