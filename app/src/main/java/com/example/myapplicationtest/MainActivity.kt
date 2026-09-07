package com.example.myapplicationtest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplicationtest.ui.theme.MyApplicationTestTheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.compose.viewModel


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            MyApplicationTestTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding),
                        contentAlignment = Alignment.Center
                    ) {
                    //  Perfilcard()
                        val viewModel: ProfileViewModel = viewModel()
                        Perfilcard(viewModel = viewModel)
                    }
                }
            }
        }
    }
}

@Composable
fun Perfilcard(viewModel: ProfileViewModel) {

    //var seguindo by remember { mutableStateOf(true) }
    val uiState by viewModel.uiState.collectAsState()
    val cardBackgroundColor = Color(0xFFEBE9F0)

    Card(
        modifier = Modifier
            .fillMaxWidth(0.85f)
            .padding(16.dp),
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(containerColor = cardBackgroundColor),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 32.dp, horizontal = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(

                painter = painterResource(id = R.drawable.perfil),
                contentDescription = "Foto de Perfil",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(100.dp)
                    .clip(CircleShape)
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = uiState.nome,
                fontSize = 22.sp,
                color = Color(0xFF2D2D2D)
            )
            Text(
                text = uiState.titulo,
                fontSize = 14.sp,
                color = Color.Gray
            )

            Spacer(modifier = Modifier.height(24.dp))

            Column(
                horizontalAlignment = Alignment.Start
            ) {
                ContactRow(icon = Icons.Default.Phone, text = uiState.telefone)
                ContactRow(icon = Icons.Default.AccountCircle, text = uiState.redesSocial)
                ContactRow(icon = Icons.Default.Email, text = uiState.email)
            }

            Spacer(modifier = Modifier.height(32.dp))

            Button(
                onClick = { viewModel.toggleFollow()},

                colors = ButtonDefaults.buttonColors(
                    containerColor = if (uiState.seguindo) Color(0xFF8B8B8B) else Color(0xFF1976D2)
                ),
                shape = RoundedCornerShape(20.dp),
                modifier = Modifier.width(150.dp)
            ) {
                Text(
                    text = if (uiState.seguindo) "Seguindo" else "Seguir",
                    color = Color.White
                )
            }
        }
    }
}

@Composable
fun ContactRow(icon: ImageVector, text: String) {
    Row(
        modifier = Modifier.padding(vertical = 6.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = Color(0xFF512DA8),
            modifier = Modifier.size(20.dp)
        )
        Spacer(modifier = Modifier.width(12.dp))
        Text(
            text = text,
            fontSize = 14.sp,
            color = Color(0xFF424242)
        )
    }
}