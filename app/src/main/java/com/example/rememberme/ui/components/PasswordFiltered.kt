package com.example.rememberme.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp

@Composable
fun PasswordInputFilter(
    modifier: Modifier,
    value: String,
    label: String? = null,
    singleLine: Boolean = true,
    onValueChange: (String) -> Unit,
    trailingIcon: @Composable (() -> Unit)? = null,
    visualTransformation: VisualTransformation,
    showPassword: Boolean,
    showError: Boolean = false,
    errorText: String? = null,
    isError: Boolean = false,

    ) {

    TextField(
        value = value,
        modifier = modifier
            .fillMaxWidth(),
        onValueChange = onValueChange,
        trailingIcon = trailingIcon,
        singleLine = singleLine,
        visualTransformation = visualTransformation,
        label = label?.let { { Text(it) } },


        )

    AnimatedVisibility(
        visible = showError && !errorText.isNullOrEmpty(),
        enter = expandVertically(animationSpec = tween(durationMillis = 600)),
        exit = shrinkVertically(animationSpec = tween(durationMillis = 400))
    ) {
        Text(
            text = errorText ?: "",
            color = androidx.compose.ui.graphics.Color.Red,
            modifier = Modifier.padding(8.dp),
        )

    }

}