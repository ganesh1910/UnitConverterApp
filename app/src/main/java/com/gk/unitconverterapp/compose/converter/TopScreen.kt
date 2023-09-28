package com.gk.unitconverterapp.compose.converter

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.gk.unitconverterapp.data.Conversion
import java.math.RoundingMode
import java.text.DecimalFormat

@Composable
fun TopScreen(
    modifier: Modifier = Modifier,
    conversionOptions: List<Conversion>,
    save: (Pair<String, String>) -> Unit
) {
    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        val inputText: MutableState<String> = remember { mutableStateOf("") }
        val selectedConversion: MutableState<Conversion?> = remember { mutableStateOf(null) }
        val typedValue = remember { mutableStateOf("0.0") }

        ConversionMenu(converterOptions = conversionOptions) {
            selectedConversion.value = it
            typedValue.value = "0.0"
        }
        Spacer(modifier = modifier.height(10.dp))
        selectedConversion.value?.let {
            InputBox(conversion = it, inputText = inputText) { input ->
                typedValue.value = input
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
                save(Pair(message1,message2))
            }
        }

    }
}