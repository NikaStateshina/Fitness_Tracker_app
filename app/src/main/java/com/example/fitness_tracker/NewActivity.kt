package com.example.fitness_tracker

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.painterResource
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import com.example.fitness_tracker.ui.theme.ArrowBackButton

@Composable
fun NewActivityWithBackground(navController: NavController) {
    val backgroundColor = Color(0xFF898888)
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
    ) {
        NewActivity(navController = navController)
    }
}

@Composable
fun NewActivity(navController: NavController){
        var selectedActivityIndex by remember { mutableStateOf(0) }
        var isActivityStarted by remember { mutableStateOf(false) }
        val activities = listOf(
            "Велосипед" to R.drawable.welcome_screenimage,
            "Бег" to R.drawable.welcome_screenimage,
            "Ходьба" to R.drawable.welcome_screenimage
        )
        if (!isActivityStarted) {
            ActivitySelectionBottomBar(
                activities = activities,
                selectedActivityIndex = selectedActivityIndex,
                onActivitySelected = { index ->
                    selectedActivityIndex = index
                },
                onStartActivity = { isActivityStarted = true },
                navController = navController
            )
        } else {
                ActivityInProgressScreen(
                activityName = activities[selectedActivityIndex].first,
                navController = navController
            )
        }
}





@Composable
fun ActivitySelectionBottomBar(
    activities: List<Pair<String, Int>>,
    selectedActivityIndex: Int,
    onActivitySelected: (Int) -> Unit,
    onStartActivity: () -> Unit,
    navController: NavController
) {
    ConstraintLayout(modifier = Modifier.fillMaxSize()){
        val (arrowBack, BottomTab) = createRefs()

        ArrowBackButton(navController,
            Routes.Activity,
            modifier = Modifier.constrainAs(arrowBack){
                start.linkTo(parent.start, margin = 10.dp)
                top.linkTo(parent.top, margin = 40.dp)
            }
        )

        Column(
            modifier = Modifier
                .background(Color.White)
                .padding(16.dp)
                .clip(RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp))
                .constrainAs(BottomTab) {
                bottom.linkTo(parent.bottom, 10.dp)
            },
        ) {
            Text(
                text = stringResource(R.string.LetsGo),
                style = TextStyle(
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                ),
                modifier = Modifier.padding(bottom = 16.dp, start = 20.dp, end = 20.dp)
            )

            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                itemsIndexed(activities) { index, activity ->
                    val isSelected = selectedActivityIndex == index
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(8.dp))
                            .border(
                                width = if (isSelected) 2.dp else 1.dp,
                                color = if (isSelected) Color(0xFF4B09F3) else Color.LightGray,
                                shape = RoundedCornerShape(8.dp)
                            )
                            .clickable { onActivitySelected(index) }
                            .padding(16.dp)
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Image(
                                painter = painterResource(id = activity.second),
                                contentDescription = activity.first,
                                modifier = Modifier.size(24.dp)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = activity.first,
                                style = TextStyle(
                                    fontSize = 16.sp,
                                )
                            )
                        }
                    }
                }
            }

            Button(
                onClick = onStartActivity,
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color(0xFF4B09F3),
                    contentColor = Color.White
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp)
                    .height(50.dp)
            ) {
                Text(
                    text = "Начать",
                    style = TextStyle(
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                )
            }
        }
    }
}



@Composable
fun ActivityInProgressScreen(activityName: String, navController: NavController) {
    ConstraintLayout(modifier = Modifier.fillMaxSize()) {
        val (arrowBack, BottomTab) = createRefs()

        ArrowBackButton(navController,
            Routes.Activity,
            modifier = Modifier.constrainAs(arrowBack){
                start.linkTo(parent.start, margin = 10.dp)
                top.linkTo(parent.top, margin = 40.dp)
            }
        )

        Box(
            modifier = Modifier.constrainAs(BottomTab){
                bottom.linkTo(parent.bottom, 10.dp)
            }
                .fillMaxWidth()
                .background(Color.White)
                .padding(16.dp)
                .clip(RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp))                ,
            contentAlignment = Alignment.Center
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Start
                ) {
                    Text(
                        text = activityName,
                        style = TextStyle(
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black
                        ),
                        modifier = Modifier.padding(bottom = 16.dp, start = 16.dp)
                    )
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "14 км",
                        style = TextStyle(
                            fontSize = 16.sp,
                            color = Color.Black
                        )
                    )
                    Text(
                        text = "00:01:46",
                        style = TextStyle(
                            fontSize = 16.sp,
                            color = Color.Black
                        )
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                Image(
                    painter = painterResource(R.drawable.finishbutton),
                    contentDescription = "Назад",
                )
            }
        }
    }
}

