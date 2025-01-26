package com.example.fitness_tracker

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun RegistrationScreen(navController: NavController) {
    val scrollState = rememberScrollState()
    var login by remember { mutableStateOf("") }
    var name by remember { mutableStateOf("") }
    var password1 by remember { mutableStateOf("") }
    var password2 by remember { mutableStateOf("") }
    var isMaleSelected by remember { mutableStateOf(false) }
    var isFemaleSelected by remember { mutableStateOf(false) }
    var isOtherSelected by remember { mutableStateOf(false) }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState),
            horizontalAlignment = Alignment.Start
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth().padding(top = 40.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.arrow_back),
                    contentDescription = "Назад",
                    modifier = Modifier
                        .clickable { navController.navigate(Routes.GreetingPreview) }
                )
                Spacer(modifier = Modifier.width(16.dp))
                Text(
                    text = stringResource(R.string.HeaderRegistration),
                    style = TextStyle(
                        fontFamily = FontFamily.SansSerif,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF101010),
                        textAlign = TextAlign.Start
                    )
                )
            }


            OutlinedTextField(
                value = login,
                onValueChange = { login = it },
                label = { Text(stringResource(R.string.Login)) },
                modifier = Modifier.fillMaxWidth().padding(top = 16.dp)
            )

            // Поле для ввода имени
            OutlinedTextField(
                value = name,
                onValueChange = { name = it },
                label = { Text(stringResource(R.string.Nickname)) },
                modifier = Modifier.fillMaxWidth().padding(top = 16.dp)
            )

            var PasswordWindow1 by remember { mutableStateOf(false) }
            OutlinedTextField(
                value = password1,
                onValueChange = { password1 = it },
                label = { Text(stringResource(R.string.Pass1)) },
                visualTransformation = if (PasswordWindow1) VisualTransformation.None else PasswordVisualTransformation(),
                trailingIcon = {
                    IconButton(onClick = { PasswordWindow1 = !PasswordWindow1 }) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_eye),
                            contentDescription = null
                        )
                    }
                },
                modifier = Modifier.fillMaxWidth().padding(top = 16.dp)
            )

            var PasswordWindow2 by remember { mutableStateOf(false) }
            OutlinedTextField(
                value = password2,
                onValueChange = { password2 = it },
                label = { Text(stringResource(R.string.Pass2)) },
                visualTransformation = if (PasswordWindow2) VisualTransformation.None else PasswordVisualTransformation(),
                trailingIcon = {
                    IconButton(onClick = { PasswordWindow2 = !PasswordWindow2 }) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_eye),
                            contentDescription = null
                        )
                    }
                },
                modifier = Modifier.fillMaxWidth().padding(top = 16.dp)
            )

            Column(
                verticalArrangement = Arrangement.spacedBy(15.dp),
                modifier = Modifier.fillMaxWidth().padding(top = 16.dp)
            ) {
                Text(
                    text = stringResource(R.string.Sex),
                    style = TextStyle(
                        fontFamily = FontFamily.SansSerif,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF101010)
                    ),
                    modifier = Modifier.padding(bottom = 8.dp)
                )

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.clickable {
                        isMaleSelected = true
                        isFemaleSelected = false
                        isOtherSelected = false
                    }
                ) {
                    Image(
                        painter = painterResource(
                            id = if (isMaleSelected) R.drawable.pressed_marker else R.drawable.marker
                        ),
                        contentDescription = null
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(stringResource(R.string.Male))
                }

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.clickable {
                        isFemaleSelected = true
                        isMaleSelected = false
                        isOtherSelected = false
                    }
                ) {
                    Image(
                        painter = painterResource(
                            id = if (isFemaleSelected) R.drawable.pressed_marker else R.drawable.marker
                        ),
                        contentDescription = null
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(stringResource(R.string.Female))
                }

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.clickable {
                        isOtherSelected = true
                        isMaleSelected = false
                        isFemaleSelected = false
                    }
                ) {
                    Image(
                        painter = painterResource(
                            id = if (isOtherSelected) R.drawable.pressed_marker else R.drawable.marker
                        ),
                        contentDescription = null
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(stringResource(R.string.Other))
                }
            }

            Button(
                onClick = { /* Регистрация */ },
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(
                    Color(0xFF4b0af2), Color(0xFFfafafa)
                ),
                modifier = Modifier.fillMaxWidth().padding(top = 20.dp)
                    .height(45.dp)
            ) {
                Text(
                    text = stringResource(R.string.regButtonText),
                    style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Bold)
                )
            }
            val part1 = stringResource(R.string.AgreementTextPart1)
            val privacyPolicy = stringResource(R.string.PrivacyPolicy)
            val part2 = stringResource(R.string.AgreementTextPart2)
            val userAgreement = stringResource(R.string.UserAgreement)
            val annotatedString = buildAnnotatedString {
                append(part1)
                append(" ")
                withStyle(style = SpanStyle(color = Color(0xFF4b0af2))) {
                    append(privacyPolicy)
                }
                append(" ")
                append(part2)
                append(" ")
                withStyle(style = SpanStyle(color = Color(0xFF4b0af2))) {
                    append(userAgreement)
                }
            }
            Text(
                text = annotatedString,
                style = TextStyle(fontSize = 12.sp, lineHeight = 20.sp),
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(top = 20.dp)
            )
        }
    }
}


