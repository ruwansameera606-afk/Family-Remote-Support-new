package com.example.familyremotesupport

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.familyremotesupport.ui.screens.HomeScreen

object Destinations {
    const val HOME = "home"
    const val START_SUPPORT = "start_support"
    const val JOIN_SUPPORT = "join_support"
    const val CONSENT = "consent"
    const val ACTIVE_SESSION = "active_session"
    const val FAMILY = "family"
    const val HISTORY = "history"
    const val SETTINGS = "settings"
}

@Composable
fun NavGraph(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = Destinations.HOME
    ) {

        composable(Destinations.HOME) {
            HomeScreen(
                navController = navController
            )
        }

        composable(Destinations.START_SUPPORT) {
            SimpleScreen("Start Support")
        }

        composable(Destinations.JOIN_SUPPORT) {
            SimpleScreen("Join Support")
        }

        composable(Destinations.CONSENT) {
            SimpleScreen("Support Permission")
        }

        composable(Destinations.ACTIVE_SESSION) {
            SimpleScreen("Active Support Session")
        }

        composable(Destinations.FAMILY) {
            SimpleScreen("My Family")
        }

        composable(Destinations.HISTORY) {
            SimpleScreen("Support History")
        }

        composable(Destinations.SETTINGS) {
            SimpleScreen("Settings")
        }
    }
}

@Composable
private fun SimpleScreen(title: String) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.headlineMedium
        )
    }
}
