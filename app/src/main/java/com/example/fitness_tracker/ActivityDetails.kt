package com.example.fitness_tracker

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.navigation.NavController

@Composable
fun ActivityMyDetails(navController: NavController) {
    val tabIcons = listOf(
        R.drawable.sportsselected,
        R.drawable.personnonselected
    )

    var selectedBottomTab by remember { mutableStateOf(0) }
    ConstraintLayout (modifier = Modifier.fillMaxSize())
    {
        val (TopRow, ColumnData, Comment, BottomTab) = createRefs()
        Row(
            modifier = Modifier.constrainAs(TopRow) {
                top.linkTo(parent.top, 30.dp)
                start.linkTo(parent.start, 20.dp)
            },
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Image(
                painter = painterResource(id = R.drawable.arrow_back),
                contentDescription = "Назад",
                modifier = Modifier
                    .clickable { navController.navigate(Routes.Activity) }
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = "Велосипед",
                style = TextStyle(
                    fontFamily = FontFamily.SansSerif,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF101010),
                    textAlign = TextAlign.Start
                ),
                modifier = Modifier.padding(start = 20.dp)
            )
            Image(
                painter = painterResource(id = R.drawable.deleteicon),
                contentDescription = "Удалить",
                modifier = Modifier.padding(start = 150.dp)
            )
            Image(
                painter = painterResource(id = R.drawable.shareicon),
                contentDescription = "Поделиться",
                modifier = Modifier.padding(start = 20.dp)
            )
        }

        Column(
            modifier = Modifier.constrainAs(ColumnData) {
                top.linkTo(TopRow.top, margin = 80.dp)
                start.linkTo(parent.start, 20.dp)
            },
        )
        {
        Text(
            text = "14.32 км",
            style = TextStyle(
            fontFamily = FontFamily.SansSerif,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF101010),
            textAlign = TextAlign.Start
        )
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
            style = TextStyle(
            fontFamily = FontFamily.SansSerif,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF101010),
            textAlign = TextAlign.Start
            )
        )
            Image(
                painter = painterResource(id = R.drawable.timeline),
            contentDescription = "Время",
            modifier = Modifier.padding(top = 10.dp),
            )
        }
        var comment by remember { mutableStateOf("") }
        OutlinedTextField(
            value = comment,
            onValueChange = { comment = it },
            label = { Text(stringResource(R.string.Comment)) },
            modifier = Modifier.fillMaxWidth().constrainAs(Comment){
                top.linkTo(ColumnData.top, 170.dp)
                start.linkTo(parent.start, margin = 20.dp)
                end.linkTo(parent.end, margin = 30.dp)
                width = Dimension.fillToConstraints
            },
        )

        TabRow(
            selectedTabIndex = selectedBottomTab,
            backgroundColor = Color.White,
            indicator = {},
            contentColor = Color.Black,
            modifier = Modifier.height(90.dp).constrainAs(BottomTab){
                bottom.linkTo(parent.bottom)
            },

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