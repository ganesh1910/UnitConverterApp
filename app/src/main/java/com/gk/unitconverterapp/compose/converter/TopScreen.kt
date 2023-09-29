package com.gk.unitconverterapp.compose.converter

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.gk.unitconverterapp.data.Conversion
import java.math.RoundingMode
import java.text.DecimalFormat

@Composable
fun TopScreen(
    modifier: Modifier = Modifier,
    conversionOptions: List<Conversion>,
    inputText: MutableState<String>,
    selectedConversion: MutableState<Conversion?>,
    typedValue: MutableState<String>,
    isLandscape: Boolean,
    save: (Pair<String, String>) -> Unit
) {
    var isSave by remember { mutableStateOf(false) }

    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier.verticalScroll(scrollState)
    ) {

        ConversionMenu(
            converterOptions = conversionOptions,
            isLandscape = isLandscape
        ) {
            selectedConversion.value = it
            typedValue.value = "0.0"
        }
        Spacer(modifier = modifier.height(10.dp))
        selectedConversion.value?.let {
            InputBox(
                conversion = it,
                inputText = inputText,
                isLandscape = isLandscape,
            ) { input ->
                typedValue.value = input
                isSave = true
            }
        }

        if (typedValue.value != "0.0") {
            val input = typedValue.value.toDouble()
            selectedConversion.value?.let {
                val result = input * it.multiplyBy

                // rounding off the result to 4 decimal places
                val df = DecimalFormat("#.####")
                df.roundingMode = RoundingMode.DOWN
                val roundedResult = df.format(result)

                val message1 = "${typedValue.value} ${it.convertFrom} is equal to"
                val message2 = "$roundedResult ${it.convertTo}"
                ResultBlock(message1 = message1, message2 = message2)
                if (isSave) {
                    save(Pair(message1, message2))
                    isSave = false
                }
            }
        }

    }
}