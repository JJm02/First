// Note the package name matches your folder
package com.example.training.ui.theme

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun TeacherMenuScreen(navController: NavController) {
    val customPurple = Color(0xFF7A59C6)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(80.dp))
        Text(text = "MENU", fontSize = 32.sp, fontWeight = FontWeight.Bold, fontFamily = FontFamily.Monospace)
        Spacer(modifier = Modifier.height(40.dp))
        Text(text = "Welcome Teacher", fontSize = 24.sp, fontFamily = FontFamily.Monospace)
        Spacer(modifier = Modifier.height(48.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = "Students:", fontSize = 20.sp, fontWeight = FontWeight.Bold, fontFamily = FontFamily.Monospace)
            Button(
                onClick = { /* TODO */ },
                shape = ShapeDefaults.ExtraLarge,
                colors = ButtonDefaults.buttonColors(containerColor = customPurple)
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Add Student Icon")
                Text(text = "Add Student")
            }
        }
        Spacer(modifier = Modifier.height(24.dp))

        // This will call the card we are about to create
        StudentInfoCard(
            studentName = "Student One",
            buttonColor = customPurple,
            onSeeInfoClick = {
                // This is the navigation action!
                navController.navigate("student_detail/Student One")
            }
        )
        Spacer(modifier = Modifier.height(16.dp))

        StudentInfoCard(
            studentName = "Student Two",
            buttonColor = customPurple,
            onSeeInfoClick = {
                // Navigate with the correct name
                navController.navigate("student_detail/Student Two")
            }
        )
    }
}