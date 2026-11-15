package com.example.ecoroute

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ecoroute.ui.theme.HeaderBackground
import com.example.ecoroute.ui.theme.HeaderText

// Header sa taas ng bawat page.
@Composable
fun AppHeader(title: String) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(HeaderBackground)   // dark gray na background
            .statusBarsPadding()            // para hindi dumikit sa battery/oras
            .padding(horizontal = 20.dp, vertical = 20.dp)
    ) {
        Text(
            text = title,
            color = HeaderText,             // puti
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )
    }
}