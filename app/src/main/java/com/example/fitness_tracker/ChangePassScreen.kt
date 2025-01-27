package com.example.fitness_tracker

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.fitness_tracker.ui.theme.ArrowBackButton
import com.example.fitness_tracker.ui.theme.PasswordTextbox

@Composable
fun ChangePassScreen(navController: NavController){
    val tabIcons = listOf(
        R.drawable.ic_sports_non_selected,
        R.drawable.ic_person_selected
    )

    var selectedBottomTab by rememberSaveable { mutableStateOf(1) }

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 30.dp)
        ) {
            Row(modifier = Modifier.padding(top = 10.dp, start = 20.dp)) {
                ArrowBackButton(navController,
                    Routes.ProfileScreen,
                    modifier = Modifier.padding(top = 5.dp))

                Text(
                    text = stringResource(R.string.ChangePass),
                    style = TextStyle(
                        fontFamily = FontFamily.SansSerif,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF101010),
                        textAlign = TextAlign.Start
                    ),
                    modifier = Modifier.padding(start = 15.dp)
                )
            }

            PasswordTextbox(
                modifier = Modifier.padding(top = 20.dp, end = 16.dp, start = 16.dp),
                R.string.OldPass
            )

            PasswordTextbox(
                modifier = Modifier.padding(end = 16.dp, start = 16.dp),
                R.string.NewPass
            )

            PasswordTextbox(
                modifier = Modifier.padding(end = 16.dp, start = 16.dp),
                R.string.Pass2
            )


            Button(
                onClick = { },
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(
                    Color(0xFF4B09F3), Color(0xFFfafafa)
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp, start = 16.dp, end = 16.dp)
                    .height(45.dp)
            ) {
                Text(
                    text = stringResource(R.string.Accept),
                    style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Bold)
                )
            }
        }

        TabRow(
            selectedTabIndex = selectedBottomTab,
            backgroundColor = Color.White,
            indicator = {},
            contentColor = Color.Black,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .height(90.dp)
        ) {
            tabIcons.forEachIndexed { index, iconRes ->
                Tab(
                    selected = selectedBottomTab == index,
                    onClick = {
                        selectedBottomTab = index
                        if (index == 0) {
                            navController.navigate(Routes.Activity)
                        } else if (index == 1) {
                            navController.navigate(Routes.ProfileScreen)
                        }
                    },
                    text = {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Image(
                                painter = painterResource(id = iconRes),
                                contentDescription = null,
                                modifier = Modifier.size(24.dp)
                            )
                            androidx.compose.material.Text(
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