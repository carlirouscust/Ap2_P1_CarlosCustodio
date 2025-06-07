package edu.ucne.ap2_p1_carloscustodio.data.sistema

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import edu.ucne.ap2_p1_carloscustodio.data.local.entities.tareaEntity

@Composable
fun TareaListScreen(
    onEdit: (tareaEntity) -> Unit,
    onCreate: () -> Unit,
    onDelete: (tareaEntity) -> Unit,
    viewModel: TareaViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    TareaListBodyScreen(
        uiState = uiState,
        goToTarea = onEdit,
        createTarea = onCreate,
        onDelete = onDelete
    )
}

@Composable
fun TareaListBodyScreen(
    uiState: tareaUiState,
    goToTarea: (tareaEntity) -> Unit,
    createTarea: () -> Unit,
    onDelete: (tareaEntity) -> Unit
) {
    val colorScheme = androidx.compose.material3.MaterialTheme.colorScheme

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = createTarea,
                containerColor = colorScheme.primary,
                contentColor = colorScheme.onPrimary
            ) {
                Icon(Icons.Filled.Add, contentDescription = "Crear tarea")
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(colorScheme.surfaceVariant, colorScheme.background)
                    )
                )
                .padding(paddingValues)
                .padding(horizontal = 18.dp, vertical = 18.dp)
        ) {
            Text(
                text = "Lista de Tareas",
                style = TextStyle(
                    fontSize = 23.sp,
                    fontWeight = FontWeight.Bold,
                    color = colorScheme.onBackground,
                    textAlign = TextAlign.Center
                ),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(24.dp))

            if (uiState.tarea.isEmpty()) {
                Text(
                    text = "No hay tareas registradas.",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 40.dp),
                    textAlign = TextAlign.Center,
                    color = colorScheme.outline
                )
            } else {
                LazyColumn(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                    items(uiState.tarea) { tarea ->
                        TareaRow(
                            tarea = tarea,
                            goToTarea = goToTarea,
                            onDelete = onDelete
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun TareaRow(
    tarea: tareaEntity,
    goToTarea: (tareaEntity) -> Unit,
    onDelete: (tareaEntity) -> Unit
) {
    val colorScheme = androidx.compose.material3.MaterialTheme.colorScheme

    Card(
        elevation = CardDefaults.cardElevation(8.dp),
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp)),
        colors = CardDefaults.cardColors(
            containerColor = colorScheme.surface
        )
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text(
                    text = "Descripción: ${tarea.descripcion}",
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    color = colorScheme.onSurface
                )
                Spacer(modifier = Modifier.height(6.dp))
                Text(
                    text = "Tiempo: ${tarea.tiempo} min",
                    fontSize = 14.sp,
                    color = colorScheme.outline
                )
            }

            Row {
                IconButton(onClick = { goToTarea(tarea) }) {
                    Icon(
                        imageVector = Icons.Filled.Edit,
                        contentDescription = "Editar tarea",
                        tint = Color.Green
                    )
                }
                IconButton(onClick = { onDelete(tarea) }) {
                    Icon(
                        imageVector = Icons.Filled.Delete,
                        contentDescription = "Eliminar tarea",
                        tint = Color.Red
                    )
                }
            }
        }
    }
}
