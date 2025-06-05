package edu.ucne.ap2_p1_carloscustodio.Presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import edu.ucne.ap2_p1_carloscustodio.data.sistema.TareaViewModel
import edu.ucne.ap2_p1_carloscustodio.data.sistema.TareaListScreen
import edu.ucne.ap2_p1_carloscustodio.data.sistema.TareaScreen

@Composable
fun HostNavigation(
    navHostController: NavHostController,
    viewModel: TareaViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    NavHost(
        navController = navHostController,
        startDestination = "TareaList"
    ) {
        composable("TareaList") {
            TareaListScreen(
                tareaList = uiState.tarea,
                onEdit = { tarea ->
                    viewModel.editarTarea(tarea)
                    navHostController.navigate("TareaForm")
                },
                onCreate = {
                    viewModel.cancelarEdicion()
                    navHostController.navigate("TareaForm")
                },
                onDelete = { tarea ->
                    viewModel.delete(tarea)
                }
            )
        }

        composable("TareaForm") {
            LaunchedEffect(uiState.successMessage) {
                if (uiState.successMessage != null) {
                    navHostController.popBackStack()
                    viewModel.cancelarEdicion()
                }
            }

            TareaScreen(
                descripcion = uiState.descripcion,
                tiempo = uiState.tiempo, // <-- parámetro agregado
                onTiempoChange = { viewModel.onTiempoChange(it) },
                onDescripcionChange = { viewModel.onDescripcionChange(it) },
                onGuardar = {
                    viewModel.guardarTarea()
                },
                onCancel = {
                    viewModel.cancelarEdicion()
                    navHostController.popBackStack()
                },
                editando = uiState.tareaEditandaId != null,
                errorMessage = uiState.errorMessage,
                successMessage = uiState.successMessage
            )
        }
    }
}