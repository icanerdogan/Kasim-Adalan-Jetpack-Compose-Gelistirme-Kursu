package com.ibrahimcanerdogan.jetpackcomposegelitirmekursu.compose4material

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ibrahimcanerdogan.jetpackcomposegelitirmekursu.compose4material.ui.theme.JetpackComposeGeliştirmeKursuTheme

class ComposeTopBarActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackComposeGeliştirmeKursuTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MaterialPage()
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MaterialPagePreview() {
    JetpackComposeGeliştirmeKursuTheme {
        MaterialPage()
    }
}

@Composable
fun MaterialPage() {
    ScaffoldSearchExample()
}

// SCAFFOLD TOP BAR
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScaffoldSearchExample() {
    val activeSearch = remember { mutableStateOf(false) }
    val textSearch = remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                colors = topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                actions = {
                    if (activeSearch.value) {
                        IconButton(onClick = {
                            activeSearch.value = false
                            textSearch.value = ""
                        }) {
                            Icon(imageVector = Icons.Default.Close, contentDescription = "")
                        }
                    } else {
                        IconButton(onClick = {
                            activeSearch.value = true
                        }) {
                            Icon(imageVector = Icons.Default.Search, contentDescription = "")
                        }
                    }
                },
                title = {
                    if (activeSearch.value) {
                        TextField( value = textSearch.value,
                            onValueChange = {
                                textSearch.value = it
                            },
                            label = { Text(text = "Ara") })
                    } else {
                        Text("Bu bir top bar title!")
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            Text(
                modifier = Modifier.padding(8.dp),
                text =
                """
                    This is an example of a scaffold. It uses the Scaffold composable's parameters to create a screen with a simple top app bar, bottom app bar, and floating action button.

                    It also contains some basic inner content, such as this text.

                """.trimIndent(),
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScaffoldExample() {
    var menuExpanded = remember { mutableStateOf(false) }
    var presses by remember { mutableIntStateOf(0) }

    Scaffold(
        topBar = {
            TopAppBar(
                colors = topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                actions = {
                    Text(text = "Filtrele", modifier = Modifier.clickable {
                        Log.e("TAG", "Filtrele Seçildi!")
                    })
                    IconButton(onClick = { Log.e("TAG", "Info Tıklandı!") }) {
                        Icon(imageVector = Icons.Default.Info, contentDescription = "")
                    }
                    IconButton(onClick = {
                        menuExpanded.value = true
                    }) {
                        Icon(imageVector = Icons.Default.MoreVert, contentDescription = "")
                    }

                    DropdownMenu(
                        expanded = menuExpanded.value,
                        onDismissRequest = { menuExpanded.value = false }) {
                        DropdownMenuItem(
                            text = { Text(text = "Güncelle") },
                            onClick = {
                                Log.e("TAG", "Güncelle Tıklandı!")
                                menuExpanded.value = false
                            })
                        DropdownMenuItem(
                            text = { Text(text = "Sil") },
                            onClick = {
                                Log.e("TAG", "Sil Tıklandı!")
                                menuExpanded.value = false
                            })
                    }
                },
                title = {
                    Column {
                        Text("Bu bir top bar title!")
                        Text("Bu bir top bar subtitle!", fontSize = 12.sp)
                    }
                }
            )
        },
        bottomBar = {
            BottomAppBar(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                contentColor = MaterialTheme.colorScheme.primary,
            ) {
                Column {
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center,
                        text = "Bottom app bar title"
                    )
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center,
                        text = "Bottom app bar subtitle",
                        fontSize = 12.sp
                    )
                }
            }
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { presses++ }) {
                Icon(Icons.Default.Add, contentDescription = "Add")
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            Text(
                modifier = Modifier.padding(8.dp),
                text =
                """
                    This is an example of a scaffold. It uses the Scaffold composable's parameters to create a screen with a simple top app bar, bottom app bar, and floating action button.

                    It also contains some basic inner content, such as this text.

                    You have pressed the floating action button $presses times.
                """.trimIndent(),
            )
        }
    }
}