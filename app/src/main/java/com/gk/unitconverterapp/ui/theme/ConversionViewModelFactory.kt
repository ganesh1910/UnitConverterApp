package com.gk.unitconverterapp.ui.theme

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider.NewInstanceFactory
import com.gk.unitconverterapp.ConversionViewModel
import com.gk.unitconverterapp.data.ConverterRepository

class ConversionViewModelFactory(
    private val repository: ConverterRepository
) : NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ConversionViewModel(repository = repository) as T
    }
}