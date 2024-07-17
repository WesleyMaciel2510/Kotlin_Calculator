package com.example.calculatorapp.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.fillMaxWidth
import java.lang.reflect.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Alignment
import androidx.compose.ui.tooling.preview.Preview
import com.example.calculatorapp.screens.App
import com.example.calculatorapp.ui.theme.CalculatorAppTheme

@Composable
fun GridLayoutComponent() {
    Column(
        //modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Row 1
        Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            GridItem(text = "7")
            GridItem(text = "8")
            GridItem(text = "9")
            GridItem(text = "/")
        }

        // Row 2
        Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            GridItem(text = "4")
            GridItem(text = "5")
            GridItem(text = "6")
            GridItem(text = "*")
        }

        // Row 3
        Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            GridItem(text = "1")
            GridItem(text = "2")
            GridItem(text = "3")
            GridItem(text = "-")
        }

        // Row 4
        Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            GridItem(text = "0")
            GridItem(text = ".")
            GridItem(text = "=")
            GridItem(text = "+")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GridLayoutComponentPreview() {
    CalculatorAppTheme {
        GridLayoutComponent()
    }
}

