package com.gk.unitconverterapp

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gk.unitconverterapp.data.Conversion
import com.gk.unitconverterapp.data.ConversionResult
import com.gk.unitconverterapp.data.ConverterRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ConversionViewModel(
    private val repository: ConverterRepository
) : ViewModel() {

    val inputText: MutableState<String> = mutableStateOf("")
    val selectedConversion: MutableState<Conversion?> = mutableStateOf(null)
    val typedValue = mutableStateOf("0.0")

    fun getConversion() = listOf(
        Conversion(1, "Pounds to Kilograms", "lbs", "kg", 0.453592),
        Conversion(2, "Kilograms to Pounds", "kg", "lbs", 2.20462),
        Conversion(3, "Yards to Meters", "yd", "m", 0.9144),
        Conversion(4, "Meters to Yards", "m", "yd", 1.09361),
        Conversion(5, "Miles to Kilometers", "mi", "km", 1.60934),
        Conversion(6, "Kilometers to Miles", "km", "mi", 0.621371)
    )

    val historyResults = repository.getSavedResults()

    fun addResult(pair: Pair<String, String>) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertResult(
                ConversionResult(
                    id = 0,
                    message1 = pair.first,
                    message2 = pair.second
                )
            )
        }
    }

    fun deleteConversion(conversionResult: ConversionResult) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteResult(result = conversionResult)
        }
    }

    fun clearAllResults() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAllResults()
        }
    }
}