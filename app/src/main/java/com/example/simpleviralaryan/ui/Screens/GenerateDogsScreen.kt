// ui/screens/GenerateDogsScreen.kt
package com.example.simpleviralaryan.ui.screens

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.simpleviralaryan.viewmodel.GenerateDogsViewModel

@Composable
fun GenerateDogsScreen(
    onNavigateBack: () -> Unit,
    viewModel: GenerateDogsViewModel
) {
    // Define the button color
    val buttonColor = Color(66, 134, 244)

    // Collect state from ViewModel
    val imageUrl by viewModel.imageUrl.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val errorMessage by viewModel.errorMessage.collectAsState()

    // UI Layout
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Back Button
        Button(
            onClick = onNavigateBack,
            colors = androidx.compose.material3.ButtonDefaults.buttonColors(containerColor = buttonColor),
            modifier = Modifier
                .align(Alignment.Start)
        ) {
            Text(text = "Back", color = Color.White)
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Generate Button
        Button(
            onClick = {
                viewModel.generateDogImage()
            },
            colors = androidx.compose.material3.ButtonDefaults.buttonColors(containerColor = buttonColor),
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        ) {
            Text(text = "Generate!", color = Color.White)
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Display loading indicator or error message or image
        when {
            isLoading -> {
                CircularProgressIndicator()
            }
            errorMessage != null -> {
                Text(text = errorMessage!!, color = Color.Red)
                Log.e("GenerateDogsScreen", "Error: $errorMessage")
            }
            imageUrl != null -> {
                Image(
                    painter = rememberAsyncImagePainter(imageUrl),
                    contentDescription = "Dog Image",
                    modifier = Modifier
                        .size(250.dp)
                        .padding(8.dp)
                )
            }
        }
    }
}
