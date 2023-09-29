package com.gk.unitconverterapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider.NewInstanceFactory
import com.gk.unitconverterapp.data.ConverterRepository
import javax.inject.Inject

class ConversionViewModelFactory @Inject constructor(
    private val repository: ConverterRepository
) : NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ConversionViewModel(repository = repository) as T
    }
}