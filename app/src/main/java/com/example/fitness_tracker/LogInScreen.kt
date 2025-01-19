package com.example.fitness_tracker

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.navigation.NavController
import com.example.fitness_tracker.ui.theme.ArrowBackButton
import com.example.fitness_tracker.ui.theme.LoginNicknameTextbox
import com.example.fitness_tracker.ui.theme.PasswordTextbox

@Composable
fun LogInScreen(navController: NavController) {
    ConstraintLayout(modifier = Modifier.fillMaxSize()) {
        val (ArrowBack, Header, Image, LoginField, PassField, LogInButton) = createRefs()

        ArrowBackButton(navController, Routes.GreetingPreview,
            modifier = Modifier.constrainAs(ArrowBack) {
                start.linkTo(parent.start, margin = 10.dp)
                top.linkTo(parent.top, margin = 40.dp)
            }
        )

        Text(
            text = stringResource(R.string.HeaderLogIn),
            modifier = Modifier.constrainAs(Header) {
                start.linkTo(ArrowBack.end, margin = 16.dp)
                top.linkTo(ArrowBack.top)
                bottom.linkTo(ArrowBack.bottom)
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
            painter = painterResource(id = R.drawable.welcome_screenimage),
            contentDescription = "",
            modifier = Modifier
                .constrainAs(Image) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    top.linkTo(parent.top, margin = 150.dp)
                }
                .height(250.dp),
            contentScale = ContentScale.Fit
        )

        LoginNicknameTextbox(
            modifier = Modifier
                .constrainAs(LoginField) {
                    top.linkTo(Image.bottom, margin = 16.dp)
                    start.linkTo(parent.start, margin = 16.dp)
                    end.linkTo(parent.end, margin = 16.dp)
                    width = Dimension.fillToConstraints
                },
            R.string.Login
        )

        PasswordTextbox(
            modifier = Modifier
                .constrainAs(PassField) {
                    top.linkTo(LoginField.bottom, margin = 16.dp)
                    start.linkTo(parent.start, margin = 16.dp)
                    end.linkTo(parent.end, margin = 16.dp)
                    width = Dimension.fillToConstraints
                },
            R.string.Pass1
        )

        Button(
            //на данный момент для удобства, чтобы можно было как-то перейти к этому экрану
            onClick = {navController.navigate(Routes.Activity) },
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.buttonColors(
                Color(0xFF4b0af2), Color(0xFFfafafa)
            ),
            modifier = Modifier
                .constrainAs(LogInButton) {
                    top.linkTo(PassField.bottom, margin = 24.dp)
                    start.linkTo(parent.start, margin = 16.dp)
                    end.linkTo(parent.end, margin = 16.dp)
                    width = Dimension.fillToConstraints
                }
                .height(45.dp)
        ) {
            Text(
                text = stringResource(R.string.LogInButtonText),
                style = TextStyle(
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
            )
        }
    }
}
