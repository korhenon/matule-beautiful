package com.example.matulebeautiful.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.matulebeautiful.R
import com.example.matulebeautiful.ui.theme.poppins

@Composable
fun PasswordField(value: String, onValueChanged: (String) -> Unit) {
    var isVisible by remember { mutableStateOf(false) }
    TextField(
        value = value,
        onValueChange = onValueChanged,
        modifier = Modifier
            .fillMaxWidth()
            .height(52.dp),
        textStyle = TextStyle(
            fontFamily = poppins,
            fontSize = 14.sp,
            color = colorScheme.onBackground
        ),
        placeholder = {
            Row(horizontalArrangement = Arrangement.spacedBy(3.dp)) {
                repeat(8) {
                    Box(
                        modifier = Modifier
                            .size(6.dp)
                            .background(colorScheme.onSurface, CircleShape)
                    )
                }
            }
        },
        shape = RoundedCornerShape(14.dp),
        colors = TextFieldDefaults.colors(
            unfocusedContainerColor = colorScheme.surface,
            focusedContainerColor = colorScheme.surface,
            unfocusedIndicatorColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
        ),
        trailingIcon = {
            Image(
                painter = painterResource(id = R.drawable.eye),
                contentDescription = "",
                Modifier.clickable {
                    isVisible = !isVisible
                }
            )
        },
        visualTransformation = if (isVisible) VisualTransformation.None else PasswordVisualTransformation()
    )
}