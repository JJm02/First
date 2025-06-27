package com.example.training

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.training.ui.theme.StudentDetailScreen
import com.example.training.ui.theme.TeacherMenuScreen
import com.example.training.ui.theme.TrainingTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TrainingTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    // 1. Create a NavController
                    val navController = rememberNavController()

                    // 2. Create a NavHost, which is a container for your screens
                    NavHost(navController = navController, startDestination = "menu") {

                        // 3. Define the "menu" screen
                        composable(route = "menu") {
                            TeacherMenuScreen(navController = navController)
                        }

                        // 4. Define the "student_detail" screen, with a dynamic argument for the name
                        composable(
                            route = "student_detail/{studentName}",
                            arguments = listOf(navArgument("studentName") { type = NavType.StringType })
                        ) { backStackEntry ->
                            // Extract the name from the route
                            val studentName = backStackEntry.arguments?.getString("studentName")
                            StudentDetailScreen(navController = navController, studentName = studentName)
                        }
                    }
                }
            }
        }
    }
}