package com.gk.unitconverterapp.compose

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.gk.unitconverterapp.ConversionViewModel
import com.gk.unitconverterapp.compose.converter.TopScreen
import com.gk.unitconverterapp.compose.history.HistoryScreen
import com.gk.unitconverterapp.ui.theme.ConversionViewModelFactory


@Composable
fun BaseScreen(
    factory: ConversionViewModelFactory,
    modifier: Modifier = Modifier,
    viewModel: ConversionViewModel = viewModel(factory = factory)
) {
    val options = viewModel.getConversion()
    val results = viewModel.historyResults.collectAsState(initial = emptyList())
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(30.dp)
    ) {
        TopScreen(
            conversionOptions = options
        ) {
            viewModel.addResult(pair = it)
        }
        Spacer(modifier = Modifier.height(10.dp))
        HistoryScreen(
            results = results,
            onDelete = {
                viewModel.deleteConversion(conversionResult = it)
            },
            onClearAll = {
                viewModel.clearAllResults()
            }
        )
    }
}