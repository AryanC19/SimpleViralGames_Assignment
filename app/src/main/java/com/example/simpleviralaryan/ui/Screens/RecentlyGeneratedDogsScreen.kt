// ui/screens/RecentlyGeneratedDogsScreen.kt
package com.example.simpleviralaryan.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.*
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
    viewModel: RecentlyGeneratedDogsViewModel
) {
    // Define the button color
    val buttonColor = Color(66, 134, 244)

    // Collect recent dog images from ViewModel
    val recentDogImages by viewModel.recentDogImages.collectAsState()

    // UI Layout
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
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

        Spacer(modifier = Modifier.height(16.dp))

        // Scrollable Gallery of Images
        if (recentDogImages.isEmpty()) {
            Text(text = "No images to display.", color = Color.Gray)
        } else {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(4.dp),
                verticalArrangement = Arrangement.spacedBy(4.dp),
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                items(recentDogImages) { dogImage ->
                    Image(
                        painter = rememberAsyncImagePainter(dogImage.imageUrl),
                        contentDescription = "Dog Image",
                        modifier = Modifier
                            .fillMaxWidth()
                            .aspectRatio(1f)
                    )
                }
            }
        }
    }
}
