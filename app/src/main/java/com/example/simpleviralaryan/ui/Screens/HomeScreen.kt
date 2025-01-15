// ui/screens/HomeScreen.kt
package com.example.simpleviralaryan.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.simpleviralaryan.viewmodel.HomeViewModel

@Composable
fun HomeScreen(
    onNavigateToGenerateDogs: () -> Unit,
    onNavigateToRecentlyGeneratedDogs: () -> Unit,
    viewModel: HomeViewModel
) {
    // Define the button color
    val buttonColor = Color(66, 134, 244)

    // Center the buttons vertically and horizontally
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Generate Dogs Button
        Button(
            onClick = onNavigateToGenerateDogs,
            colors = androidx.compose.material3.ButtonDefaults.buttonColors(containerColor = buttonColor),
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        ) {
            Text(text = "Generate Dogs", color = Color.White)
        }

        // My Recently Generated Dogs Button
        Button(
            onClick = onNavigateToRecentlyGeneratedDogs,
            colors = androidx.compose.material3.ButtonDefaults.buttonColors(containerColor = buttonColor),
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        ) {
            Text(text = "My Recently Generated Dogs", color = Color.White)
        }
    }
}
