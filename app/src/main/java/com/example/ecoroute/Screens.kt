package com.example.ecoroute

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp

// Page 1: Report
@Composable
fun ReportScreen() {
    Column(modifier = Modifier.fillMaxSize()) {
        AppHeader(title = "Submit report")


        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(text = "Report Page", fontSize = 24.sp)
        }
    }
}

// Page 2: Report history
@Composable
fun ReportHistoryScreen() {
    Column(modifier = Modifier.fillMaxSize()) {
        AppHeader(title = "Report history")

        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(text = "Report History Page", fontSize = 24.sp)
        }
    }
}

// Page 3: More
@Composable
fun MoreScreen() {
    Column(modifier = Modifier.fillMaxSize()) {
        AppHeader(title = "More")

        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(text = "More Page", fontSize = 24.sp)
        }
    }
}