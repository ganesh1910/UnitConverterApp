package com.gk.unitconverterapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.gk.unitconverterapp.compose.BaseScreen
import com.gk.unitconverterapp.data.ConverterDatabase
import com.gk.unitconverterapp.data.ConverterRepositoryImpl
import com.gk.unitconverterapp.ui.theme.ConversionViewModelFactory
import com.gk.unitconverterapp.ui.theme.UnitConverterAppTheme

class MainActivity : ComponentActivity() {
    private lateinit var database: ConverterDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        database = ConverterDatabase.getInstance(context = this)
        val dao = database.getConvertDAO()
        val repository = ConverterRepositoryImpl(dao = dao)
        val factory = ConversionViewModelFactory(repository = repository)
        setContent {
            UnitConverterAppTheme {
                BaseScreen(factory = factory)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        database.close()
    }
}