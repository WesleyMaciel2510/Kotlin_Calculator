package com.example.calculatorapp.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.calculatorapp.ui.theme.CalculatorAppTheme
import com.example.calculatorapp.ui.theme.DarkColorScheme
import com.example.calculatorapp.ui.theme.LightColorScheme

@Composable
fun GridItem(text: String) {
    Box(
        modifier = Modifier
            .size(64.dp)
            .background(DarkColorScheme.primary, RoundedCornerShape(15.dp))
            .clickable { /* Handle click events if needed */ }
            .padding(8.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            color = LightColorScheme.background,
            style = MaterialTheme.typography.bodyLarge,
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