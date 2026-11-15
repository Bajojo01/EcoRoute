package com.example.ecoroute

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.ecoroute.ui.theme.NavBackground
import com.example.ecoroute.ui.theme.NavSelected
import com.example.ecoroute.ui.theme.NavUnselected

// yung 3 buttons sa baba ng screen.
@Composable
fun AppBottomBar(navController: NavController) {

    // Listahan ng 3 pages (galing sa Screen.kt)
    val items = listOf(Screen.Report, Screen.ReportHistory, Screen.More)

    // check kung anong page nakabukas
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = backStackEntry?.destination?.route

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(NavBackground)        // light gray na bar
            .navigationBarsPadding()          // para hindi matakpan ng gesture bar
            .padding(vertical = 12.dp),       // space sa taas at baba ng bar
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        items.forEach { screen ->
            val selected = currentRoute == screen.route
            val color = if (selected) NavSelected else NavUnselected

            Column(
                modifier = Modifier
                    .weight(1f)
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null
                    ) {
                        // Lilipat sa page na pinindot
                        navController.navigate(screen.route) {
                            popUpTo(Screen.Report.route) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    },
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    imageVector = if (selected) screen.selectedIcon else screen.icon,
                    contentDescription = screen.label,
                    tint = color,
                    modifier = Modifier.size(24.dp)
                )


                Spacer(modifier = Modifier.height(2.dp))

                Text(
                    text = screen.label,
                    color = color,
                    fontSize = 12.sp,
                    fontWeight = if (selected) FontWeight.Bold else FontWeight.Normal
                )
            }
        }
    }
}