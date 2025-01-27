package com.example.fitness_tracker.ui.theme

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import com.example.fitness_tracker.R

@Composable
fun PasswordTextbox(modifier: Modifier = Modifier, @StringRes labelResId: Int){
    var PasswordWindow by remember { mutableStateOf(false) }
    var password by remember { mutableStateOf("") }
    OutlinedTextField(
        value = password,
        onValueChange = { password = it },
        label = { Text(stringResource(labelResId)) },
        modifier = modifier.fillMaxWidth(),
        visualTransformation = if (PasswordWindow) VisualTransformation.None else PasswordVisualTransformation(),
        trailingIcon = {
            IconButton(onClick = { PasswordWindow = !PasswordWindow }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_eye),
                    contentDescription = null
                )
            }
        }

    )
}