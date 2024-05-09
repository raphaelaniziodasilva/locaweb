package br.com.fiap.locaweb

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import br.com.fiap.locaweb.frontEnd.screen.CreateAccountScreen
import br.com.fiap.locaweb.frontEnd.screen.CreateAccountScreenViewModel
import br.com.fiap.locaweb.frontEnd.screen.InboxScreen
import br.com.fiap.locaweb.frontEnd.screen.LoginScreen
import br.com.fiap.locaweb.frontEnd.screen.LoginScreenViewModel
import br.com.fiap.locaweb.frontEnd.screen.MessageScreen
import br.com.fiap.locaweb.frontEnd.screen.MessageScreenViewModel
import br.com.fiap.locaweb.ui.theme.LocawebTheme
import com.google.accompanist.navigation.animation.rememberAnimatedNavController

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalAnimationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LocawebTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberAnimatedNavController()

                    NavHost(
                        navController = navController,
                        startDestination = "login"
                    ){
                        composable(route = "login"){
                            LoginScreen(navController, LoginScreenViewModel())
                        }
                        composable(route = "createAccountScreen"){
                            CreateAccountScreen(navController, CreateAccountScreenViewModel())
                        }
                        composable(route = "messageScreen"){
                            MessageScreen(MessageScreenViewModel())
                        }
                        composable(route = "inboxScreen"){
                            InboxScreen()
                        }

                    }
                }
            }
        }
    }
}