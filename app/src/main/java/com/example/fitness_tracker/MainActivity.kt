package com.example.fitness_tracker
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
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
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.fitness_tracker.ui.theme.Fitness_TrackerTheme


object Routes {
    const val GreetingPreview = "GreetingPreview"
    const val Registration_screen = "Registration_screen"
    const val Log_In_screen = "Log_In_screen"
}


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Fitness_TrackerTheme {
                // Передаем управление в MainApp, чтобы передать navController
                MainApp()
            }
        }
    }
}


@Composable
fun MainApp() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Routes.GreetingPreview
    ) {
        composable(Routes.GreetingPreview) {
            GreetingPreview(navController)
        }
        composable(Routes.Registration_screen) {
            Registration_screen(navController)
        }
        composable(Routes.Log_In_screen){
        Log_In_screen(navController)
        }
    }
}



@Composable
fun GreetingPreview(navController: NavController) {
    ConstraintLayout(modifier = Modifier.fillMaxSize()){
        val (welcomeimage, greetingText, greetingSubText, regButton, ExAcc) = createRefs()

        Image(painter = painterResource(id = R.drawable.welcome_screenimage),
            contentDescription = "",
            modifier = Modifier.constrainAs(welcomeimage) {
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                top.linkTo(parent.top, 150.dp)
            }
        )

        Text(
            text=stringResource(R.string.welcome_image_description),
            modifier = Modifier.constrainAs(greetingText){
                width = Dimension.fillToConstraints
                start.linkTo(parent.start, margin = 20.dp)
                end.linkTo(parent.end, margin = 20.dp)
                top.linkTo(welcomeimage.bottom, margin = 25.dp)
            },
            style = TextStyle(
                fontFamily = FontFamily.SansSerif,
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp,
                color = Color.Black,
                textAlign = TextAlign.Center
            )
        )

        Text(
            text= stringResource(R.string.welcome_subheadline),
            modifier = Modifier.constrainAs(greetingSubText){
                width = Dimension.fillToConstraints
                top.linkTo(greetingText.top, margin = 80.dp)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            },
            style = TextStyle(
                fontFamily = FontFamily.SansSerif,
                fontSize = 16.sp,
                color = Color.Gray,
                textAlign = TextAlign.Center
            )
        )

        Button(
            onClick = { navController.navigate(Routes.Registration_screen)},
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.buttonColors(
                Color(0xFF4b0af2), Color(0xFFfafafa)
            ),
            modifier = Modifier.size(200.dp, 50.dp)
                .constrainAs(regButton){
                width = Dimension.fillToConstraints
                top.linkTo(greetingSubText.top, margin = 70.dp)
                start.linkTo(parent.start, 96.dp)
                end.linkTo(parent.end, 96.dp)
            },
        ){
                Text(
                    stringResource(R.string.regButtonText),
                    style = TextStyle(
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,)
                )
        }

        Text(
            text = stringResource(R.string.ExAcc),
            modifier = Modifier
                .constrainAs(ExAcc) {
                    width = Dimension.fillToConstraints
                    top.linkTo(regButton.top, margin = 80.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
                .clickable {
                    navController.navigate(Routes.Log_In_screen)
                },
            style = TextStyle(
                fontFamily = FontFamily.SansSerif,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF4b0af2),
                textAlign = TextAlign.Center
            )
        )
    }
}

@Composable
fun Registration_screen(navController: NavController) {
    val scrollState = rememberScrollState() // Запоминаем состояние прокрутки

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .verticalScroll(scrollState) // Добавляем вертикальную прокрутку
                .padding(16.dp)
        ) {
            ConstraintLayout(modifier = Modifier.fillMaxSize().padding(16.dp)) {
                val (arrowBack, regHeader, sexHeader, man, manText, woman, womanText,
                    other, otherText, loginField, nameField, passField1,
                    passField2, registrationButton, bigText) = createRefs()

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
                    modifier = Modifier.constrainAs(arrowBack) {
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
                    modifier = Modifier.constrainAs(regHeader) {
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
                    modifier = Modifier.constrainAs(loginField) {
                        top.linkTo(regHeader.bottom, margin = 16.dp)
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
                    modifier = Modifier.constrainAs(nameField) {
                        top.linkTo(loginField.bottom, margin = 16.dp)
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
                    modifier = Modifier.constrainAs(passField1) {
                        top.linkTo(nameField.bottom, margin = 16.dp)
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
                    modifier = Modifier.constrainAs(passField2) {
                        top.linkTo(passField1.bottom, margin = 16.dp)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        width = Dimension.fillToConstraints
                    }
                )



                Text(
                    text = stringResource(R.string.Sex),
                    modifier = Modifier.constrainAs(sexHeader) {
                        start.linkTo(parent.start)
                        top.linkTo(passField2.top, margin = 100.dp)
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
                    modifier = Modifier.constrainAs(man) {
                        start.linkTo(parent.start)
                        top.linkTo(sexHeader.top, margin = 40.dp)
                    }
                        .clickable {
                            isMaleSelected = true
                            isOtherSelected = false
                            isFemaleSelected = false
                        }
                )
                Text(
                    text = stringResource(R.string.Male),
                    modifier = Modifier.constrainAs(manText) {
                        start.linkTo(man.start, margin = 40.dp)
                        top.linkTo(sexHeader.top, margin = 40.dp)
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
                    modifier = Modifier.constrainAs(woman) {
                        start.linkTo(parent.start)
                        top.linkTo(man.top, margin = 40.dp)
                    }
                        .clickable {
                            isFemaleSelected = true
                            isOtherSelected = false
                            isMaleSelected = false
                        }
                )
                Text(
                    text = stringResource(R.string.Female),
                    modifier = Modifier.constrainAs(womanText) {
                        start.linkTo(woman.start, margin = 40.dp)
                        top.linkTo(man.top, margin = 40.dp)
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
                    modifier = Modifier.constrainAs(other) {
                        start.linkTo(parent.start)
                        top.linkTo(woman.top, margin = 40.dp)
                    }
                        .clickable {
                            isOtherSelected = true
                            isFemaleSelected = false
                            isMaleSelected = false
                        }
                )
                Text(
                    text = stringResource(R.string.Other),
                    modifier = Modifier.constrainAs(otherText) {
                        start.linkTo(other.start, margin = 40.dp)
                        top.linkTo(woman.top, margin = 40.dp)
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
                        .constrainAs(registrationButton) {
                            width = Dimension.fillToConstraints
                            top.linkTo(other.top, margin = 70.dp)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                        },
                ) {
                    Text(stringResource(R.string.regButtonText),
                        style = TextStyle(
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold)
                    )
                }

                val part1 = stringResource(R.string.agreement_text_part1)
                val privacyPolicy = stringResource(R.string.privacy_policy)
                val part2 = stringResource(R.string.agreement_text_part2)
                val userAgreement = stringResource(R.string.user_agreement)
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
                    modifier = Modifier.constrainAs(bigText) {
                        top.linkTo(registrationButton.top, margin = 90.dp)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
                )
            }
        }
    }
}

@Composable
fun Log_In_screen(navController: NavController) {
    ConstraintLayout(modifier = Modifier.fillMaxSize()) {
        var login by remember { mutableStateOf("") }
        var password by remember { mutableStateOf("") }
        val (arrowBack, header, image, loginField, passField, loginButton) = createRefs()
        Image(
            painter = painterResource(id = R.drawable.arrow_back),
            contentDescription = "Назад",
            modifier = Modifier.constrainAs(arrowBack) {
                start.linkTo(parent.start, margin = 10.dp)
                top.linkTo(parent.top, margin = 40.dp)
            }
                .clickable {
                    navController.navigate(Routes.GreetingPreview)
                }

        )
        // Заголовок "Вход"
        Text(
            text = stringResource(R.string.Header_Log_In),
            modifier = Modifier.constrainAs(header) {
                start.linkTo(arrowBack.start, margin = 40.dp)
                top.linkTo(parent.top, margin = 36.dp)
            },
            style = TextStyle(
                fontFamily = FontFamily.SansSerif,

                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF101010),
                textAlign = TextAlign.Center
            )
        )
        Image(painter = painterResource(id = R.drawable.welcome_screenimage),
            contentDescription = "",
            modifier = Modifier.constrainAs(image) {
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                top.linkTo(parent.top, 150.dp)
            }
                .height(250.dp),
            contentScale = ContentScale.Fit
        )
        // Поле для ввода логина
        OutlinedTextField(
            value = login,
            onValueChange = { login = it },
            label = { Text(stringResource(R.string.Login)) },
            modifier = Modifier.constrainAs(loginField) {
                top.linkTo(image.bottom, margin = 16.dp)
                start.linkTo(parent.start, margin = 15.dp)
                end.linkTo(parent.end, margin = 15.dp)
                width = Dimension.fillToConstraints
            }
        )
        // Поле для ввода пароля с иконкой "показать/скрыть пароль"
        var passwordVisible1 by remember { mutableStateOf(false) }
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
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
            modifier = Modifier.constrainAs(passField) {
                top.linkTo(loginField.bottom, margin = 16.dp)
                start.linkTo(parent.start, margin = 15.dp)
                end.linkTo(parent.end, margin = 15.dp)
                width = Dimension.fillToConstraints
            }
        )
        Button(
            onClick = { },
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.buttonColors(
                Color(0xFF4b0af2), Color(0xFFfafafa)
            ),
            modifier = Modifier.size(200.dp, 50.dp)
                .constrainAs(loginButton) {
                    width = Dimension.fillToConstraints
                    top.linkTo(passField.top, margin = 85.dp)
                    start.linkTo(parent.start, margin = 15.dp)
                    end.linkTo(parent.end, margin = 15.dp)
                },
        ) {
            Text(stringResource(R.string.Log_In_ButtonText),
                style = TextStyle(
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,))
        }
    }
}

