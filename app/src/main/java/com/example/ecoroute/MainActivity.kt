package com.example.ecoroute

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.ecoroute.ui.theme.EcoRouteTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            EcoRouteTheme {
                // Taga lipat ng page
                val navController = rememberNavController()

                // check kung anong page ang nakabukas
                val backStackEntry by navController.currentBackStackEntryAsState()
                val currentRoute = backStackEntry?.destination?.route

                // papakita lang yung bottom bar sa 3 main pages
                val showBottomBar = currentRoute == Screen.Report.route ||
                        currentRoute == Screen.ReportHistory.route ||
                        currentRoute == Screen.More.route

                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    contentWindowInsets = WindowInsets(0.dp),
                    bottomBar = { if (showBottomBar) AppBottomBar(navController) }
                ) { innerPadding ->

                    NavHost(
                        navController = navController,
                        startDestination = Routes.LOGIN,   // Log in ang unang bubukas
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        // Log in
                        composable(Routes.LOGIN) {
                            LoginScreen(
                                onLoginSuccess = {
                                    navController.navigate(Screen.Report.route) {
                                        popUpTo(Routes.LOGIN) { inclusive = true }
                                    }
                                },
                                onSignUpClick = { navController.navigate(Routes.SIGNUP) }
                            )
                        }

                        // Sign up
                        composable(Routes.SIGNUP) {
                            SignUpScreen(
                                onBackClick = { navController.popBackStack() },
                                onSignUpSuccess = {
                                    navController.navigate(Screen.Report.route) {
                                        popUpTo(Routes.LOGIN) { inclusive = true }
                                    }
                                },
                                onLogInClick = { navController.popBackStack() }
                            )
                        }

                        // 3 main pages
                        composable(Screen.Report.route) { ReportScreen() }
                        composable(Screen.ReportHistory.route) { ReportHistoryScreen() }
                        composable(Screen.More.route) { MoreScreen() }
                    }
                }
            }
        }
    }
}