package com.example.familyremotesupport

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

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
            SimpleScreen("Family Remote Support")
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
    androidx.compose.foundation.layout.Box(
        modifier = androidx.compose.ui.Modifier.fillMaxSize(),
        contentAlignment = androidx.compose.ui.Alignment.Center
    ) {
        androidx.compose.material3.Text(
            text = title,
            style = androidx.compose.material3.MaterialTheme.typography.headlineMedium
        )
    }
}
