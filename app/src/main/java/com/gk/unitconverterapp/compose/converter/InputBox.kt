package com.gk.unitconverterapp.compose.converter

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.gk.unitconverterapp.data.Conversion

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InputBox(
    conversion: Conversion,
    inputText: MutableState<String>,
    modifier: Modifier = Modifier,
    context: Context = LocalContext.current,
    calculate: (String) -> Unit
) {
    Column(
        modifier = modifier.padding(top = 20.dp)
    ) {
        Row(
            modifier = modifier.fillMaxWidth()
        ) {
            TextField(
                modifier = modifier.width(100.dp),
                value = inputText.value,
                onValueChange = { inputText.value = it },
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    capitalization = KeyboardCapitalization.None,
                    autoCorrect = true,
                    keyboardType = KeyboardType.Number
                ),
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = MaterialTheme.colorScheme.surface.copy(alpha = 0.3F)
                ),
                textStyle = TextStyle(color = Color.DarkGray, fontSize = 30.sp)
            )
            Spacer(modifier = modifier.width(10.dp))
            Text(
                text = "Convert ${conversion.convertFrom} to ${conversion.convertTo}",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                modifier = modifier
                    .padding(start = 10.dp, top = 30.dp)
                    .fillMaxWidth()
            )
        }
        Spacer(modifier = modifier.height(20.dp))
        OutlinedButton(
            onClick = {
                if (inputText.value.isNotBlank()) {
                    calculate(inputText.value)
                } else {
                    Toast.makeText(context, "Enter value", Toast.LENGTH_SHORT).show()
                }
            },
            modifier = modifier.fillMaxWidth(1F),
            shape = RoundedCornerShape(5.dp)
        ) {
            Text(
                text = "Convert",
                fontSize = 36.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Blue
            )
        }
    }
}