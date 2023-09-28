package com.gk.unitconverterapp.compose.history

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import com.gk.unitconverterapp.data.ConversionResult

@Composable
fun HistoryList(
    results: State<List<ConversionResult>>,
    modifier: Modifier = Modifier,
    onCloseTask: (ConversionResult) -> Unit
) {

    LazyColumn {
        items(
            items = results.value,
            key = { item ->
                item.id
            }
        ) {
            HistoryItem(
                pair = Pair(it.message1, it.message2),
                onClose = {
                    onCloseTask(it)
                }
            )
        }
    }
}