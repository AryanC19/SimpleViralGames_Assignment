// ui/screens/RecentlyGeneratedDogsScreen.kt
package com.example.simpleviralaryan.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.simpleviralaryan.viewmodel.RecentlyGeneratedDogsViewModel
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun RecentlyGeneratedDogsScreen(
    onNavigateBack: () -> Unit,
    viewModel: RecentlyGeneratedDogsViewModel = viewModel()
) {
    // Define the button color
    val buttonColor = Color(66, 134, 244)

    // Collect recent dog images from ViewModel
    val recentDogImages by viewModel.recentDogImages.collectAsState()

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
            modifier = Modifier.align(Alignment.Start)
        ) {
            Text(text = "Back", color = Color.White)
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Scrollable Gallery of Images in a Horizontal Row
        if (recentDogImages.isNotEmpty()) {
            LazyRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp), // Adjust height as needed
                horizontalArrangement = Arrangement.spacedBy(4.dp),
                contentPadding = PaddingValues(horizontal = 0.dp)
            ) {
                items(recentDogImages) { dogImage ->
                    Image(
                        painter = rememberAsyncImagePainter(dogImage.imageUrl),
                        contentDescription = "Dog Image",
                        modifier = Modifier
                            .width(200.dp) // Adjust width as needed
                            .height(250.dp) // Match the LazyRow height
                    )
                }
            }
        } else {
            Text(text = "No images to display.", color = Color.Gray)
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Clear Dogs Button
        Button(
            onClick = {
                viewModel.clearDogs()
            },
            colors = androidx.compose.material3.ButtonDefaults.buttonColors(containerColor = buttonColor),
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        ) {
            Text(text = "Clear Dogs", color = Color.White)
        }
    }
}
