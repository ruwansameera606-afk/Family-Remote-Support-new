package com.family.remotesupport.ui.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.family.remotesupport.ui.screens.*
import com.family.remotesupport.viewmodel.SupportSessionViewModel
import com.family.remotesupport.viewmodel.FamilyViewModel

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
    navController: NavHostController,
    sessionViewModel: SupportSessionViewModel = viewModel(),
    familyViewModel: FamilyViewModel = viewModel()
) {
    NavHost(
        navController = navController,
        startDestination = Destinations.HOME
    ) {
        composable(Destinations.HOME) {
            HomeScreen(
                navController = navController,
                sessionViewModel = sessionViewModel
            )
        }
        composable(Destinations.START_SUPPORT) {
            StartSupportScreen(
                navController = navController,
                sessionViewModel = sessionViewModel
            )
        }
        composable(Destinations.JOIN_SUPPORT) {
            JoinSupportScreen(
                navController = navController,
                sessionViewModel = sessionViewModel
            )
        }
        composable(Destinations.CONSENT) {
            ConsentScreen(
                navController = navController,
                sessionViewModel = sessionViewModel
            )
        }
        composable(Destinations.ACTIVE_SESSION) {
            ActiveSessionScreen(
                navController = navController,
                sessionViewModel = sessionViewModel
            )
        }
        composable(Destinations.FAMILY) {
            FamilyScreen(
                navController = navController,
                familyViewModel = familyViewModel
            )
        }
        composable(Destinations.HISTORY) {
            HistoryScreen(
                navController = navController,
                sessionViewModel = sessionViewModel
            )
        }
        composable(Destinations.SETTINGS) {
            SettingsScreen(
                navController = navController
            )
        }
    }
}
