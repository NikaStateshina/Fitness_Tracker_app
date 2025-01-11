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
    const val RegistrationScreen = "RegistrationScreen"
    const val LogInScreen = "LogInScreen"
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
        composable(Routes.RegistrationScreen) {
            RegistrationScreen(navController)
        }
        composable(Routes.LogInScreen){
        LogInScreen(navController)
        }
    }
}



@Composable
fun GreetingPreview(navController: NavController) {
    ConstraintLayout(modifier = Modifier.fillMaxSize()){
        val (WelcomeImage, GreetingText, GreetingSubText, RegButton, ExAcc) = createRefs()

        Image(painter = painterResource(id = R.drawable.welcome_screenimage),
            contentDescription = "",
            modifier = Modifier.constrainAs(WelcomeImage) {
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                top.linkTo(parent.top, 150.dp)
            }
        )

        Text(
            text=stringResource(R.string.WelcomeImageDescription),
            modifier = Modifier.constrainAs(GreetingText){
                width = Dimension.fillToConstraints
                start.linkTo(parent.start, margin = 20.dp)
                end.linkTo(parent.end, margin = 20.dp)
                top.linkTo(WelcomeImage.bottom, margin = 25.dp)
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
            text= stringResource(R.string.WelcomeSubheadLine),
            modifier = Modifier.constrainAs(GreetingSubText){
                width = Dimension.fillToConstraints
                top.linkTo(GreetingText.top, margin = 80.dp)
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
            onClick = { navController.navigate(Routes.RegistrationScreen)},
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.buttonColors(
                Color(0xFF4b0af2), Color(0xFFfafafa)
            ),
            modifier = Modifier.size(200.dp, 50.dp)
                .constrainAs(RegButton){
                width = Dimension.fillToConstraints
                top.linkTo(GreetingSubText.top, margin = 70.dp)
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
                    top.linkTo(RegButton.top, margin = 80.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
                .clickable {
                    navController.navigate(Routes.LogInScreen)
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




