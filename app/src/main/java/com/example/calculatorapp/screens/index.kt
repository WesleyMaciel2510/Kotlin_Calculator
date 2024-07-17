package com.example.calculatorapp.screens


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.calculatorapp.ui.theme.CalculatorAppTheme
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import com.example.calculatorapp.components.GridLayoutComponent
import com.example.calculatorapp.ui.theme.CalculatorAppTheme
import com.example.calculatorapp.ui.theme.DarkColorScheme

@Composable
fun App() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .background(DarkColorScheme.background, RoundedCornerShape(8.dp))
        ) {
            // Display content here
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Horizontal gray dash
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .background(Color.Gray)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Grid for numbers and operators (4x5)
        GridLayoutComponent()
    }
}

@Preview(showBackground = true)
@Composable
fun AppPreview() {
    CalculatorAppTheme {
        App()
    }
}