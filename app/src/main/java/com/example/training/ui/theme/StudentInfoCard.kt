// Note the package name is the same
package com.example.training.ui.theme

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun StudentInfoCard(
    studentName: String,
    buttonColor: Color,
    onSeeInfoClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        border = BorderStroke(1.dp, Color.Black)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = studentName,
                fontFamily = FontFamily.Monospace,
                fontSize = 16.sp
            )
            Button(
                onClick = onSeeInfoClick, // <-- Use the passed-in lambda
                shape = RoundedCornerShape(50),
                colors = ButtonDefaults.buttonColors(containerColor = buttonColor)
            ) {
                Text(text = "See Info")
            }
        }
    }
}