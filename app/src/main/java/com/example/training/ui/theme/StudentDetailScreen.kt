package com.example.training.ui.theme

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ChatBubbleOutline
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.StarOutline
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

// An enum to represent our tabs. This is cleaner than using strings.
enum class StudentDetailTab {
    History,
    Progress
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StudentDetailScreen(navController: NavController, studentName: String?) {
    val customPurple = Color(0xFF7A59C6)

    // 1. STATE MANAGEMENT: This variable holds the currently selected tab.
    // We start with Progress selected to match your image.
    var selectedTab by remember { mutableStateOf(StudentDetailTab.History) }

    Scaffold(
        bottomBar = {
            // Pass the state and the click handler to the bottom bar
            AppBottomNavigationBar(
                currentTab = selectedTab,
                onTabSelected = { newTab -> selectedTab = newTab },
                selectedColor = customPurple
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 24.dp)
        ) {
            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = { navController.navigateUp() },
                colors = ButtonDefaults.buttonColors(containerColor = customPurple)
            ) {
                Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back to Menu")
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = "Menu")
            }

            Spacer(modifier = Modifier.height(32.dp))

            Text(
                text = studentName ?: "Student Detail",
                fontSize = 28.sp,
                fontFamily = FontFamily.Monospace,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )

            Spacer(modifier = Modifier.height(16.dp))

            WireframePlaceholder(modifier = Modifier.height(100.dp))

            Spacer(modifier = Modifier.height(40.dp))

            // 2. DYNAMIC CONTENT: Use a 'when' block to show the correct content.
            when (selectedTab) {
                StudentDetailTab.History -> HistoryContent(buttonColor = customPurple)
                StudentDetailTab.Progress -> ProgressContent(buttonColor = customPurple)
            }
        }
    }
}

// New Content Composable for the Progress Tab
@Composable
fun ProgressContent(buttonColor: Color) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = "Progress:",
            fontSize = 20.sp,
            fontFamily = FontFamily.Monospace,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(16.dp))
        DetailItemCard(buttonColor = buttonColor) // Reusing the card
        Spacer(modifier = Modifier.height(16.dp))
        DetailItemCard(buttonColor = buttonColor) // Reusing the card
    }
}

// Extracted Content Composable for the History Tab
@Composable
fun HistoryContent(buttonColor: Color) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = "History:",
            fontSize = 20.sp,
            fontFamily = FontFamily.Monospace,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(16.dp))
        DetailItemCard(buttonColor = buttonColor) // Reusing the card
        Spacer(modifier = Modifier.height(16.dp))
        DetailItemCard(buttonColor = buttonColor) // Reusing the card
    }
}

// 3. DYNAMIC BOTTOM BAR: It now takes the current state and reports clicks.
@Composable
fun AppBottomNavigationBar(
    currentTab: StudentDetailTab,
    onTabSelected: (StudentDetailTab) -> Unit,
    selectedColor: Color
) {
    NavigationBar(containerColor = Color(0xFFF1EDF9)) {
        // History Tab
        NavigationBarItem(
            selected = (currentTab == StudentDetailTab.History),
            onClick = { onTabSelected(StudentDetailTab.History) },
            label = { Text("History") },
            icon = { Icon(Icons.Filled.ChatBubbleOutline, contentDescription = "History") },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = selectedColor,
                selectedTextColor = selectedColor,
                indicatorColor = Color(0xFFE0D6F3),
                unselectedIconColor = Color.Gray,
                unselectedTextColor = Color.Gray
            )
        )
        // Progress Tab
        NavigationBarItem(
            selected = (currentTab == StudentDetailTab.Progress),
            onClick = { onTabSelected(StudentDetailTab.Progress) },
            label = { Text("Progress") },
            icon = { Icon(Icons.Filled.Star, contentDescription = "Progress") },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = selectedColor,
                selectedTextColor = selectedColor,
                indicatorColor = Color(0xFFE0D6F3),
                unselectedIconColor = Color.Gray,
                unselectedTextColor = Color.Gray
            )
        )
    }
}

// RENAMED from HistoryItemCard to DetailItemCard for reusability
@Composable
fun DetailItemCard(buttonColor: Color) {
    Surface(
        shape = RoundedCornerShape(12.dp),
        border = BorderStroke(1.dp, Color.Black)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 8.dp, top = 8.dp, bottom = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            WireframePlaceholder(modifier = Modifier.height(50.dp).weight(1f))
            Spacer(modifier = Modifier.width(16.dp))
            Button(
                onClick = { /* TODO: See Details click */ },
                shape = RoundedCornerShape(50),
                colors = ButtonDefaults.buttonColors(containerColor = buttonColor)
            ) {
                Text(text = "See Details")
            }
        }
    }
}


// This placeholder function remains unchanged
@Composable
fun WireframePlaceholder(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .border(BorderStroke(1.dp, Color.Black), shape = RoundedCornerShape(8.dp))
    ) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            val canvasWidth = size.width
            val canvasHeight = size.height
            drawLine(
                color = Color.Black,
                start = Offset(x = 0f, y = 0f),
                end = Offset(x = canvasWidth, y = canvasHeight),
                strokeWidth = 1.dp.toPx()
            )
            drawLine(
                color = Color.Black,
                start = Offset(x = canvasWidth, y = 0f),
                end = Offset(x = 0f, y = canvasHeight),
                strokeWidth = 1.dp.toPx()
            )
        }
    }
}


// The preview function remains unchanged
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun StudentDetailScreenPreview() {
    TrainingTheme {
        StudentDetailScreen(navController = rememberNavController(), studentName = "Student One")
    }
}