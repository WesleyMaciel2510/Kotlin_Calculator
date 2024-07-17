package com.example.calculatorapp.components

import androidx.annotation.OptIn
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.calculatorapp.ui.theme.CalculatorAppTheme
import com.example.calculatorapp.ui.theme.DarkColorScheme
import com.example.calculatorapp.ui.theme.LightColorScheme
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.media3.common.util.Log
import androidx.media3.common.util.UnstableApi
import com.example.calculatorapp.TotalViewModel

@OptIn(UnstableApi::class)
@Composable
fun GridItem(text: String, totalViewModel: TotalViewModel = viewModel()) {
    val textColor = when (text) {
        "C" -> Color(0XFFF86C66)
        "()", "%", "/", "X", "-", "+" -> Color(0xFF318607)
        else -> LightColorScheme.background
    }

    val backgroundColor = if (text == "=") Color(0xFF318607) else DarkColorScheme.primary

    Box(
        modifier = Modifier
            .size(75.dp)
            .background(backgroundColor, RoundedCornerShape(30.dp))
            .clickable {
                Log.d("updateText", "FUNCTION CALLED###")
                Log.d("updateText", "Item clicked $text")
                totalViewModel.handleInput(text)
            }
            .padding(10.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            style = TextStyle(
                fontSize =35.sp,
                fontWeight = FontWeight.Bold
            ),
            color = textColor,
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GridItemPreview() {
    CalculatorAppTheme {
        GridItem(text = "0")
    }
}