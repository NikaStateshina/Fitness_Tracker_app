package com.example.fitness_tracker.ui.theme

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource

@Composable
fun LoginNicknameTextbox(modifier: Modifier = Modifier,  @StringRes labelResId: Int){
    var login by remember { mutableStateOf("") }
    OutlinedTextField(
        value = login,
        onValueChange = { login = it },
        label = { Text(stringResource(labelResId)) },
        modifier = modifier.fillMaxWidth(),
    )
}