package com.example.simpleviralaryan

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.simpleviralaryan.navigation.NavGraph
import com.example.simpleviralaryan.ui.theme.SimpleViralAryanTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SimpleViralAryanTheme {
                NavGraph() // Calls the NavGraph composable here
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    SimpleViralAryanTheme {

    }
}
