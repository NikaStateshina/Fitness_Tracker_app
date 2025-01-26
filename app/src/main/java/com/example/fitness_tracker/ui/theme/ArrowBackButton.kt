package com.example.fitness_tracker.ui.theme

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import com.example.fitness_tracker.R

@Composable
fun ArrowBackButton(navController: NavController,
                    destination: String,
                    modifier: Modifier = Modifier) {
    Image(
        painter = painterResource(id = R.drawable.arrow_back),
        contentDescription = "Назад",
        modifier = modifier
            .clickable { navController.navigate(destination) }
    )
}