package com.example.tugaspab

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.net.toUri
import com.example.tugaspab.ui.theme.TugasPabTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        // Mengaktifkan fitur edge-to-edge untuk tampilan modern yang memenuhi layar
        enableEdgeToEdge()
        
        // Data Kelompok yang akan ditampilkan dan dikirim ke ProfileActivity
        val nim = "L0324006"
        val nama = "ADRIAN, ARDI, DIMAS"
        val jurusan = "Informatika PSDKU"
        val angkatan = "2024"
        val deskripsi = "KAMI adalah mahasiswa yang jujur, ramah, dan berdedikasi tinggi dalam mengeksplorasi teknologi informasi di PSDKU."
        val githubUrl = "https://github.com/"

        setContent {
            TugasPabTheme {
                // MainScreen dibungkus dalam Surface untuk menangani background default
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainScreen(
                        onGoToProfile = {
                            val intent = Intent(this, ProfileActivity::class.java).apply {
                                putExtra("NIM", nim)
                                putExtra("NAMA", nama)
                                putExtra("JURUSAN", jurusan)
                                putExtra("ANGKATAN", angkatan)
                                putExtra("DESKRIPSI", deskripsi)
                            }
                            startActivity(intent)
                        },
                        onGoToGithub = {
                            try {
                                val intent = Intent(Intent.ACTION_VIEW, githubUrl.toUri())
                                startActivity(intent)
                            } catch (e: Exception) {
                                Toast.makeText(this, "Tidak ada aplikasi browser yang ditemukan", Toast.LENGTH_SHORT).show()
                            }
                        }
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    onGoToProfile: () -> Unit = {},
    onGoToGithub: () -> Unit = {}
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            CenterAlignedTopAppBar(
                title = { 
                    Text(
                        text = "Aplikasi Profil Kelompok", 
                        fontWeight = FontWeight.ExtraBold,
                        style = MaterialTheme.typography.titleLarge
                    ) 
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    titleContentColor = Color(0xFFB71C1C) // Menggunakan aksen merah yang khas
                )
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .verticalScroll(rememberScrollState()) // Mendukung scroll untuk layar kecil
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "PP GACOR",
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold,
                color = Color.DarkGray,
                modifier = Modifier.padding(bottom = 24.dp)
            )

            // Menampilkan Foto Kelompok dalam Card dengan bayangan (elevation)
            ElevatedCard(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp),
                shape = MaterialTheme.shapes.large,
                elevation = CardDefaults.elevatedCardElevation(defaultElevation = 8.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.foto_profil),
                    contentDescription = "Foto Kelompok",
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
            }

            Spacer(modifier = Modifier.height(32.dp))

            Text(
                text = "Selamat Datang!",
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 12.dp)
            )

            Text(
                text = "Terima kasih telah mengunjungi aplikasi profil kelompok kami. Di sini Anda dapat melihat informasi lengkap mengenai anggota tim kami.",
                textAlign = TextAlign.Center,
                lineHeight = 24.sp,
                color = Color.Gray,
                style = MaterialTheme.typography.bodyLarge
            )

            Spacer(modifier = Modifier.height(48.dp))

            // Tombol navigasi ke Profil dengan desain utama
            Button(
                onClick = onGoToProfile,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFB71C1C)),
                shape = MaterialTheme.shapes.medium
            ) {
                Icon(Icons.Default.AccountCircle, contentDescription = null)
                Spacer(modifier = Modifier.width(8.dp))
                Text("Lihat Profil Lengkap", fontWeight = FontWeight.Bold, fontSize = 16.sp)
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Tombol navigasi ke GitHub dengan desain outline
            OutlinedButton(
                onClick = onGoToGithub,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                shape = MaterialTheme.shapes.medium,
                border = ButtonDefaults.outlinedButtonBorder(enabled = true)
            ) {
                Icon(Icons.Default.Info, contentDescription = null)
                Spacer(modifier = Modifier.width(8.dp))
                Text("Kunjungi GitHub", fontWeight = FontWeight.SemiBold, fontSize = 16.sp)
            }
            
            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    TugasPabTheme {
        MainScreen()
    }
}
