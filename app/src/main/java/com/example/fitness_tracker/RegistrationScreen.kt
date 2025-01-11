package com.example.fitness_tracker

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.navigation.NavController

@Composable
fun RegistrationScreen(navController: NavController) {
    val scrollState = rememberScrollState() // Запоминаем состояние прокрутки

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .verticalScroll(scrollState) // Добавляем вертикальную прокрутку
                .padding(16.dp)
        ) {
            ConstraintLayout(modifier = Modifier.fillMaxSize().padding(16.dp)) {
                val (ArrowBack, RegHeader, SexHeader, Man, ManText, Woman, WomanText,
                    Other, OtherText, LoginField, NameField, PassField1,
                    PassField2, RegistrationButton, BigText) = createRefs()

                // Состояния для каждого текстового поля
                var login by remember { mutableStateOf("") }
                var name by remember { mutableStateOf("") }
                var password1 by remember { mutableStateOf("") }
                var password2 by remember { mutableStateOf("") }
                var isMaleSelected by remember { mutableStateOf(false) }
                var isFemaleSelected by remember { mutableStateOf(false) }
                var isOtherSelected by remember { mutableStateOf(false) }

                // Кнопка "Назад"
                Image(
                    painter = painterResource(id = R.drawable.arrow_back),
                    contentDescription = "Назад",
                    modifier = Modifier.constrainAs(ArrowBack) {
                        start.linkTo(parent.start, (-10).dp)
                        top.linkTo(parent.top, margin = 16.dp)
                    }
                        .clickable {
                            navController.navigate(Routes.GreetingPreview)
                        }

                )

                // Заголовок "Регистрация"
                Text(
                    text = stringResource(R.string.HeaderRegistration),
                    modifier = Modifier.constrainAs(RegHeader) {
                        start.linkTo(parent.start, (-140).dp)
                        end.linkTo(parent.end)
                        top.linkTo(parent.top, margin = 12.dp)
                    },
                    style = TextStyle(
                        fontFamily = FontFamily.SansSerif,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF101010),
                        textAlign = TextAlign.Center
                    )
                )

                // Поле для ввода логина
                OutlinedTextField(
                    value = login,
                    onValueChange = { login = it },
                    label = { Text(stringResource(R.string.Login)) },
                    modifier = Modifier.constrainAs(LoginField) {
                        top.linkTo(RegHeader.bottom, margin = 16.dp)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        width = Dimension.fillToConstraints
                    }
                )

                // Поле для ввода имени
                OutlinedTextField(
                    value = name,
                    onValueChange = { name = it },
                    label = { Text(stringResource(R.string.Nickname)) },
                    modifier = Modifier.constrainAs(NameField) {
                        top.linkTo(LoginField.bottom, margin = 16.dp)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        width = Dimension.fillToConstraints
                    }
                )

                // Поле для ввода пароля с иконкой "показать/скрыть пароль"
                var passwordVisible1 by remember { mutableStateOf(false) }
                OutlinedTextField(
                    value = password1,
                    onValueChange = { password1 = it },
                    label = { Text(stringResource(R.string.Pass1)) },
                    visualTransformation = if (passwordVisible1) VisualTransformation.None else PasswordVisualTransformation(),
                    trailingIcon = {
                        val image = R.drawable.eye
                        IconButton(onClick = { passwordVisible1 = !passwordVisible1 }) {
                            Icon(
                                painter = painterResource(id = image),
                                contentDescription = if (passwordVisible1) "Скрыть пароль" else "Показать пароль"
                            )
                        }
                    },
                    modifier = Modifier.constrainAs(PassField1) {
                        top.linkTo(NameField.bottom, margin = 16.dp)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        width = Dimension.fillToConstraints
                    }
                )

                // Поле для подтверждения пароля
                var passwordVisible2 by remember { mutableStateOf(false) }
                OutlinedTextField(
                    value = password2,
                    onValueChange = { password2 = it },
                    label = { Text(stringResource(R.string.Pass2)) },
                    visualTransformation = if (passwordVisible2) VisualTransformation.None else PasswordVisualTransformation(),
                    trailingIcon = {
                        val image = R.drawable.eye
                        IconButton(onClick = { passwordVisible2 = !passwordVisible2 }) {
                            Icon(
                                painter = painterResource(id = image),
                                contentDescription = if (passwordVisible2) "Скрыть пароль" else "Показать пароль"
                            )
                        }
                    },
                    modifier = Modifier.constrainAs(PassField2) {
                        top.linkTo(PassField1.bottom, margin = 16.dp)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        width = Dimension.fillToConstraints
                    }
                )



                Text(
                    text = stringResource(R.string.Sex),
                    modifier = Modifier.constrainAs(SexHeader) {
                        start.linkTo(parent.start)
                        top.linkTo(PassField2.top, margin = 100.dp)
                    },
                    style = TextStyle(
                        fontFamily = FontFamily.SansSerif,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF101010),
                        textAlign = TextAlign.Center
                    )
                )

                Image(
                    painter = painterResource(
                        id = if (isMaleSelected) R.drawable.pressed_marker else R.drawable.marker
                    ),
                    contentDescription = "Male",
                    modifier = Modifier.constrainAs(Man) {
                        start.linkTo(parent.start)
                        top.linkTo(SexHeader.top, margin = 40.dp)
                    }
                        .clickable {
                            isMaleSelected = true
                            isOtherSelected = false
                            isFemaleSelected = false
                        }
                )
                Text(
                    text = stringResource(R.string.Male),
                    modifier = Modifier.constrainAs(ManText) {
                        start.linkTo(Man.start, margin = 40.dp)
                        top.linkTo(SexHeader.top, margin = 40.dp)
                    },
                    style = TextStyle(
                        fontFamily = FontFamily.SansSerif,
                        fontSize = 16.sp,
                        color = Color(0xFF101010),
                        textAlign = TextAlign.Center
                    )
                )

                Image(
                    painter = painterResource(
                        id = if (isFemaleSelected) R.drawable.pressed_marker else R.drawable.marker
                    ),
                    contentDescription = "Female",
                    modifier = Modifier.constrainAs(Woman) {
                        start.linkTo(parent.start)
                        top.linkTo(Man.top, margin = 40.dp)
                    }
                        .clickable {
                            isFemaleSelected = true
                            isOtherSelected = false
                            isMaleSelected = false
                        }
                )
                Text(
                    text = stringResource(R.string.Female),
                    modifier = Modifier.constrainAs(WomanText) {
                        start.linkTo(Woman.start, margin = 40.dp)
                        top.linkTo(Man.top, margin = 40.dp)
                    },
                    style = TextStyle(
                        fontFamily = FontFamily.SansSerif,
                        fontSize = 16.sp,
                        color = Color(0xFF101010),
                        textAlign = TextAlign.Center
                    )
                )

                Image(
                    painter = painterResource(
                        id = if (isOtherSelected) R.drawable.pressed_marker else R.drawable.marker
                    ),
                    contentDescription = "Other",
                    modifier = Modifier.constrainAs(Other) {
                        start.linkTo(parent.start)
                        top.linkTo(Woman.top, margin = 40.dp)
                    }
                        .clickable {
                            isOtherSelected = true
                            isFemaleSelected = false
                            isMaleSelected = false
                        }
                )
                Text(
                    text = stringResource(R.string.Other),
                    modifier = Modifier.constrainAs(OtherText) {
                        start.linkTo(Other.start, margin = 40.dp)
                        top.linkTo(Woman.top, margin = 40.dp)
                    },
                    style = TextStyle(
                        fontFamily = FontFamily.SansSerif,
                        fontSize = 16.sp,
                        color = Color(0xFF101010),
                        textAlign = TextAlign.Center
                    )
                )

                Button(
                    onClick = { },
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.buttonColors(
                        Color(0xFF4b0af2), Color(0xFFfafafa)
                    ),
                    modifier = Modifier.size(200.dp, 50.dp)
                        .constrainAs(RegistrationButton) {
                            width = Dimension.fillToConstraints
                            top.linkTo(Other.top, margin = 70.dp)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                        },
                ) {
                    Text(
                        stringResource(R.string.regButtonText),
                        style = TextStyle(
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold)
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
                    modifier = Modifier.constrainAs(BigText) {
                        top.linkTo(RegistrationButton.top, margin = 90.dp)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
                )
            }
        }
    }
}
