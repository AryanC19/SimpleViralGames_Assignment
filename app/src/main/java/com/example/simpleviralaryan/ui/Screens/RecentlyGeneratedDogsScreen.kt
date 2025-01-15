// ui/screens/RecentlyGeneratedDogsScreen.kt
package com.example.simpleviralaryan.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.simpleviralaryan.viewmodel.RecentlyGeneratedDogsViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.ui.text.font.FontWeight

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecentlyGeneratedDogsScreen(
    onNavigateBack: () -> Unit,
    viewModel: RecentlyGeneratedDogsViewModel = viewModel()
) {
    // Define the button color
    val buttonColor = Color(66, 134, 244)

    // Collect recent dog images from ViewModel
    val recentDogImages by viewModel.recentDogImages.collectAsState()

    Scaffold(
        containerColor = Color.White,
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Recently Generated!",
                        fontWeight = FontWeight.Bold,
                        color = Color.Black,
                        fontSize = 18.sp
                    )
                },
                navigationIcon = {
                    Row(
                        modifier = Modifier
                            .clickable(onClick = onNavigateBack,
                                indication = null,  // Removes the ripple effect
                                interactionSource = remember { MutableInteractionSource() },)
                            .padding(horizontal = 8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = null,
                            tint = Color(66, 134, 244)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = "Back",
                            color = Color(66, 134, 244),
                            fontSize = 16.sp

                        )
                        Spacer(modifier = Modifier.width(30.dp))
                    }
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = Color.White,
                    navigationIconContentColor = Color.Black,
                    titleContentColor = Color.Black,
                    scrolledContainerColor = Color.White
                ),
                modifier = Modifier.border(
                    width = 1.dp,
                    color = Color.Black,
                    shape = RoundedCornerShape(0.dp)

                ).height(66.dp),
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Scrollable Gallery of Images in a Horizontal Row
            if (recentDogImages.isNotEmpty()) {
                LazyRow(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(350.dp),
                    horizontalArrangement = Arrangement.spacedBy(15.dp),
                    contentPadding = PaddingValues(horizontal = 0.dp)
                ) {
                    items(recentDogImages) { dogImage ->
                        Box(
                            modifier = Modifier
                                .size(300.dp)
                        ) {
                            Image(
                                painter = rememberAsyncImagePainter(dogImage.imageUrl),
                                contentDescription = "Dog Image",
                                contentScale = ContentScale.Crop,
                                modifier = Modifier.fillMaxSize()
                            )
                        }
                    }
                }
            } else {
                Text(text = "No images to display.", color = Color.Gray)
            }

            Box(
                modifier = Modifier
                    .size(width = 140.dp, height = 35.dp)
                    .border(
                        width = 3.dp,
                        color = Color.Black,
                        shape = RoundedCornerShape(50.dp)
                    )
            ) {
                Button(
                    onClick = { viewModel.clearDogs() },
                    colors = ButtonDefaults.buttonColors(containerColor = buttonColor),
                    shape = RoundedCornerShape(50.dp),
                    modifier = Modifier.fillMaxSize()
                ) {
                    Text(text = "Clear Dogs!", color = Color.White, fontSize = 16.sp)
                }
            }
        }
    }
}