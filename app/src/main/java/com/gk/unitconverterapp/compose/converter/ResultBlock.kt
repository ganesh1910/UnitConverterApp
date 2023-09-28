package com.gk.unitconverterapp.compose.converter

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ResultBlock(
    modifier: Modifier = Modifier,
    message1: String,
    message2: String
) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface.copy(0.35f)
        ),
        shape = RoundedCornerShape(10.dp),
        elevation = CardDefaults.cardElevation(5.dp),
        modifier = modifier.padding(top = 20.dp)
    ) {

        Column(
            modifier = modifier.padding(20.dp)
        ) {

            Text(text = message1, fontSize = 28.sp)
            Text(
                text = message2,
                fontSize = 28.sp,
                color = Color.Blue,
                fontWeight = FontWeight.Bold
            )
        }

    }

}