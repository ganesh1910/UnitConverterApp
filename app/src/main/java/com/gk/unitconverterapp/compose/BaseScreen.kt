package com.gk.unitconverterapp.compose

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.gk.unitconverterapp.ConversionViewModel
import com.gk.unitconverterapp.ConversionViewModelFactory
import com.gk.unitconverterapp.compose.converter.TopScreen
import com.gk.unitconverterapp.compose.history.HistoryScreen


@Composable
fun BaseScreen(
    factory: ConversionViewModelFactory,
    modifier: Modifier = Modifier,
    viewModel: ConversionViewModel = viewModel(factory = factory)
) {
    val options = viewModel.getConversion()
    val results = viewModel.historyResults.collectAsState(initial = emptyList())

    val configuration = LocalConfiguration.current
    var isLandscape by remember { mutableStateOf(false) }

    when(configuration.orientation){
        Configuration.ORIENTATION_LANDSCAPE ->{
            isLandscape = true
            Row(
                modifier = modifier
                    .padding(30.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                TopScreen(
                    conversionOptions = options,
                    inputText = viewModel.inputText,
                    selectedConversion = viewModel.selectedConversion,
                    typedValue = viewModel.typedValue,
                    isLandscape = isLandscape
                ) {
                    viewModel.addResult(pair = it)
                }
                Spacer(modifier = Modifier.width(10.dp))
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
        Configuration.ORIENTATION_PORTRAIT ->{
            isLandscape = false
            Column(
                modifier = modifier
                    .fillMaxSize()
                    .padding(30.dp)
            ) {
                TopScreen(
                    conversionOptions = options,
                    inputText = viewModel.inputText,
                    selectedConversion = viewModel.selectedConversion,
                    typedValue = viewModel.typedValue,
                    isLandscape = isLandscape
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
    }
}