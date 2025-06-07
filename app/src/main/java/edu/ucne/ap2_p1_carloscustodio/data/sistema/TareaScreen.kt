package edu.ucne.ap2_p1_carloscustodio.data.sistema

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun TareaScreen(
    viewModel: TareaViewModel = hiltViewModel()

    ) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    TareaBodyScreen(
        uiState = uiState,
        onDescripcionChange = viewModel::onDescripcionChange,
        onTiempoChange = viewModel::onTiempoChange,
        onGuardar = viewModel::guardarTarea,
        onCancelar = viewModel::cancelarEdicion
        )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TareaBodyScreen(
    uiState: tareaUiState,
    onDescripcionChange: (String) -> Unit,
    onTiempoChange: (String) -> Unit,
    onGuardar: () -> Unit,
    onCancelar: () -> Unit


    ) {
    val colorScheme = MaterialTheme.colorScheme

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = if (uiState.editando) "Editar Tarea" else "Registrar Tarea",
                        style = TextStyle(
                            fontSize = 22.sp,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center
                        ),
                        modifier = Modifier.fillMaxWidth(),
                        color = colorScheme.onSurface
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = colorScheme.background
                )
            )
        }
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            colorScheme.surfaceVariant,
                            colorScheme.background
                        )
                    )
                )
                .padding(padding)
                .padding(20.dp),
            contentAlignment = Alignment.TopCenter
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        color = colorScheme.surface,
                        shape = MaterialTheme.shapes.medium
                    )
                    .padding(24.dp),
                verticalArrangement = Arrangement.spacedBy(18.dp)
            ) {
                OutlinedTextField(
                    value = uiState.descripcion,
                    onValueChange = { onDescripcionChange(it) },
                    label = { Text("Descripción de la tarea") },
                    modifier = Modifier.fillMaxWidth(),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = colorScheme.primary,
                        unfocusedBorderColor = colorScheme.outline
                    )
                )

                OutlinedTextField(
                    value = uiState.tiempo.toString(),
                    onValueChange = { onTiempoChange(it) },
                    label = { Text("Tiempo en minutos") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    modifier = Modifier.fillMaxWidth(),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = colorScheme.primary,
                        unfocusedBorderColor = colorScheme.outline
                    )
                )

                uiState.errorMessage?.let {
                    Text(
                        text = it,
                        color = colorScheme.error,
                        modifier = Modifier.padding(top = 4.dp)
                    )
                }

                uiState.successMessage?.let {
                    Text(
                        text = it,
                        color = colorScheme.primary,
                        modifier = Modifier.padding(top = 4.dp)
                    )
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Button(
                        onClick = onCancelar,
                        modifier = Modifier
                            .weight(1f)
                            .padding(end = 8.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFFF44336),
                            contentColor = colorScheme.onError
                        )
                    ) {
                        Text("Cancelar")
                    }
                    Button(
                        onClick = onGuardar,
                        modifier = Modifier
                            .weight(1f)
                            .padding(start = 8.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF4CAF50),
                            contentColor = colorScheme.onPrimary
                        )
                    ) {
                        Text("Guardar")
                    }

                }
            }
        }
    }
}
