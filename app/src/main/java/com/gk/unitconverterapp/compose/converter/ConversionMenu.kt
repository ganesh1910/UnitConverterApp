package com.gk.unitconverterapp.compose.converter

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import com.gk.unitconverterapp.data.Conversion

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ConversionMenu(
    converterOptions: List<Conversion>,
    modifier: Modifier = Modifier,
    convert: (Conversion) -> Unit
) {

    var displayText by remember { mutableStateOf("Select conversion type") }
    var expanded by remember { mutableStateOf(false) }


    ExposedDropdownMenuBox(
        modifier = modifier.fillMaxWidth(),
        expanded = expanded,
        onExpandedChange = { expanded = !expanded }
    ) {
        OutlinedTextField(
            modifier = modifier
                .fillMaxWidth()
                .menuAnchor(),
            value = displayText,
            onValueChange = { displayText = it },
            readOnly = true,
            label = {
                Text(text = "Unit Converter")
            },
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
            }
        )
        DropdownMenu(
            modifier = modifier
                .background(color = Color.White)
                .exposedDropdownSize(),
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            converterOptions.forEach { conversion ->
                DropdownMenuItem(
                    contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding,
                    text = {
                        Text(
                            text = conversion.description,
                            fontSize = 18.sp
                        )
                    },
                    onClick = {
                        displayText = conversion.description
                        expanded = false
                        convert(conversion)
                    }
                )
            }
        }
    }
}