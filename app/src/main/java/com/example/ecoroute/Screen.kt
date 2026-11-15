package com.example.ecoroute

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.MoreHoriz
import androidx.compose.material.icons.outlined.Description
import androidx.compose.material.icons.outlined.History
import androidx.compose.material.icons.outlined.MoreHoriz
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screen(
    val route: String,
    val label: String,
    val icon: ImageVector,
    val selectedIcon: ImageVector
) {
    object Report : Screen(
        "report", "Report",
        Icons.Outlined.Description, Icons.Filled.Description
    )
    object ReportHistory : Screen(
        "report_history", "Report history",
        Icons.Outlined.History, Icons.Filled.History
    )
    object More : Screen(
        "more", "More",
        Icons.Outlined.MoreHoriz, Icons.Filled.MoreHoriz
    )
}

// Address ng Login at Sign up
object Routes {
    const val LOGIN = "login"
    const val SIGNUP = "signup"
}