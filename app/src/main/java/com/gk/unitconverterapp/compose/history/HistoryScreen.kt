package com.gk.unitconverterapp.compose.history

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.gk.unitconverterapp.data.ConversionResult

@Composable
fun HistoryScreen(
    results: State<List<ConversionResult>>,
    modifier: Modifier = Modifier,
    onDelete: (ConversionResult) -> Unit,
    onClearAll: () -> Unit
) {
    Column {
        if (results.value.isNotEmpty()) {
            Row(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(bottom = 10.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "History",
                    color = Color.Gray
                )
                OutlinedButton(
                    onClick = onClearAll,
                    shape = RoundedCornerShape(5.dp)
                ) {
                    Text(
                        text = "Clear All",
                        color = Color.Gray
                    )
                }

            }
        }

        HistoryList(results = results) {
            onDelete(it)
        }
    }
}