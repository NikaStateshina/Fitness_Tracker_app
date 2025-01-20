package com.example.fitness_tracker

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
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
fun LogInScreen(navController: NavController) {
    ConstraintLayout(modifier = Modifier.fillMaxSize()) {
        var login by remember { mutableStateOf("") }
        var password by remember { mutableStateOf("") }
        val (ArrowBack, Header, Image, LoginField, PassField, LogInButton) = createRefs()
        Image(
            painter = painterResource(id = R.drawable.arrow_back),
            contentDescription = "Назад",
            modifier = Modifier.constrainAs(ArrowBack) {
                start.linkTo(parent.start, margin = 10.dp)
                top.linkTo(parent.top, margin = 40.dp)
            }
                .clickable {
                    navController.navigate(Routes.GreetingPreview)
                }

        )

        Text(
            text = stringResource(R.string.HeaderLogIn),
            modifier = Modifier.constrainAs(Header) {
                start.linkTo(ArrowBack.start, margin = 40.dp)
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
            modifier = Modifier.constrainAs(Image) {
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                top.linkTo(parent.top, 150.dp)
            }
                .height(250.dp),
            contentScale = ContentScale.Fit
        )

        OutlinedTextField(
            value = login,
            onValueChange = { login = it },
            label = { Text(stringResource(R.string.Login)) },
            modifier = Modifier.constrainAs(LoginField) {
                top.linkTo(Image.bottom, margin = 16.dp)
                start.linkTo(parent.start, margin = 15.dp)
                end.linkTo(parent.end, margin = 15.dp)
                width = Dimension.fillToConstraints
            }
        )

        var passwordVisible1 by remember { mutableStateOf(false) }
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text(stringResource(R.string.Pass1)) },
            visualTransformation = if (passwordVisible1) VisualTransformation.None else PasswordVisualTransformation(),
            trailingIcon = {
                val image = R.drawable.ic_eye
                IconButton(onClick = { passwordVisible1 = !passwordVisible1 }) {
                    Icon(
                        painter = painterResource(id = image),
                        contentDescription = if (passwordVisible1) "Скрыть пароль" else "Показать пароль"
                    )
                }
            },
            modifier = Modifier.constrainAs(PassField) {
                top.linkTo(LoginField.bottom, margin = 16.dp)
                start.linkTo(parent.start, margin = 15.dp)
                end.linkTo(parent.end, margin = 15.dp)
                width = Dimension.fillToConstraints
            }
        )
        Button(
            //на данный момент для удобства, чтобы можно было как-то перейти к этому экрану
            onClick = {navController.navigate(Routes.Activity) },
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.buttonColors(
                Color(0xFF4b0af2), Color(0xFFfafafa)
            ),
            modifier = Modifier.fillMaxWidth().padding(start = 5.dp, end = 5.dp)
                .height(45.dp)
                .constrainAs(LogInButton) {
                    width = Dimension.fillToConstraints
                    top.linkTo(PassField.top, margin = 85.dp)
                    start.linkTo(parent.start, margin = 15.dp)
                    end.linkTo(parent.end, margin = 15.dp)
                },
        ) {
            Text(
                stringResource(R.string.LogInButtonText),
                style = TextStyle(
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,)
            )
        }
    }
}