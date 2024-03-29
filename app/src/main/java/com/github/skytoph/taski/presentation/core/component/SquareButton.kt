package com.github.skytoph.taski.presentation.core.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.github.skytoph.taski.ui.theme.TaskiTheme

@Composable
fun SquareButton(onClick: () -> Unit, icon: ImageVector, size: Dp, isEnabled: Boolean) {
    TextButton(
        onClick = onClick,
        shape = RoundedCornerShape(10),
        modifier = Modifier.size(size),
        colors = ButtonDefaults.textButtonColors(
            containerColor = MaterialTheme.colorScheme.secondary,
            contentColor = MaterialTheme.colorScheme.onTertiary,
            disabledContainerColor = MaterialTheme.colorScheme.surfaceVariant,
            disabledContentColor = MaterialTheme.colorScheme.onSurfaceVariant,
        ),
        enabled = isEnabled
    ) {
        Icon(imageVector = icon, contentDescription = icon.name, modifier = Modifier.size(16.dp))
    }
}

@Composable
@Preview
fun SquareButtonPreview() {
    TaskiTheme {
        Row {
            SquareButton({}, icon = Icons.Default.Remove, size = 60.dp, isEnabled = false)
            SquareButton({}, icon = Icons.Default.Add, size = 60.dp, isEnabled = true)
        }
    }
}

@Composable
@Preview
fun DarkSquareButtonPreview() {
    TaskiTheme(darkTheme = true) {
        Row {
            SquareButton({}, icon = Icons.Default.Remove, size = 60.dp, isEnabled = false)
            SquareButton({}, icon = Icons.Default.Add, size = 60.dp, isEnabled = true)
        }
    }
}