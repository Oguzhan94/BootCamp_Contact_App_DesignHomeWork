package com.oguzhan.contactapp.ui.addContact.components

import android.R.attr.label
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

@Composable
fun IconWithTextField(
    icon: ImageVector? = null,
    label: String,
    text: String,
    onValueChange: (String) -> Unit
) {

    val keyboardType = when (label) {
        "First Name", "Last Name" -> KeyboardType.Text
        "Email" -> KeyboardType.Email
        "Phone" -> KeyboardType.Phone
        else -> KeyboardType.Text
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 10.dp, end = 20.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (icon != null) {
            Icon(
                imageVector = icon,
                contentDescription = "Person"
            )
        } else {
            Spacer(Modifier.width(24.dp))
        }

        Spacer(Modifier.width(10.dp))
        OutlinedTextField(
            value = text,
            onValueChange = { onValueChange(it) },
            label = { Text(text = label) },
            modifier = Modifier
                .fillMaxWidth(),
            keyboardOptions = KeyboardOptions(
                keyboardType = keyboardType
            )
        )
        Spacer(Modifier.height(20.dp))
    }
}