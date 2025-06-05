package edu.ucne.ap2_p1_carloscustodio.data.sistema

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import edu.ucne.ap2_p1_carloscustodio.data.local.repository.tareaRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject
import edu.ucne.ap2_p1_carloscustodio.data.local.entities.tareaEntity

@HiltViewModel
class TareaViewModel @Inject constructor(
    private val repository: tareaRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(tareaUiState())
    val uiState: StateFlow<tareaUiState> = _uiState.asStateFlow()

    init {
        loadTareas()
    }

    private fun loadTareas() {
        viewModelScope.launch {
            repository.getAll().collect { tareas ->
                _uiState.update { it.copy(tarea = tareas) }
            }
        }
    }

    fun onDescripcionChange(newValue: String) {
        _uiState.update { it.copy(descripcion = newValue) }
    }

    fun onTiempoChange(newValue: String) {
        _uiState.update { it.copy(tiempo = newValue) }
    }

    fun editarTarea(tarea: tareaEntity) {
        _uiState.update {
            it.copy(
                descripcion = tarea.descripcion,
                tiempo = tarea.tiempo,
                tareaEditandaId = tarea.tareaId,
                errorMessage = null,
                successMessage = null
            )
        }
    }

    fun guardarTarea() {
        val state = _uiState.value


        if (state.descripcion.isBlank() || state.tiempo.isBlank()) {
            _uiState.update { it.copy(errorMessage = "Todos los campos deben estar llenos.", successMessage = null) }
            return
        }

        viewModelScope.launch {
            if (state.tareaEditandaId != null) {
                val tareaActualizada = tareaEntity(
                    tareaId = state.tareaEditandaId,
                    descripcion = state.descripcion,
                    tiempo = state.tiempo
                )
                repository.saveTarea(tareaActualizada)
                _uiState.update {
                    it.copy(
                        descripcion = "",
                        tiempo = "",
                        tareaEditandaId = null,
                        errorMessage = null,
                        successMessage = "Tarea actualizada con éxito"
                    )
                }
            } else {
                if (isDescripcionDuplicado(state.descripcion)) {
                    _uiState.update { it.copy(errorMessage = "La descripcion ya existe.", successMessage = null) }
                    return@launch
                }
                val tareaNueva = tareaEntity(
                    descripcion = state.descripcion,
                    tiempo = state.tiempo
                )
                repository.saveTarea(tareaNueva)
                _uiState.update {
                    it.copy(
                        descripcion = "",
                        tiempo = "",
                        errorMessage = null,
                        successMessage = "Tiempo guardado con éxito"
                    )
                }
            }
        }
    }

    fun cancelarEdicion() {
        _uiState.update {
            it.copy(
                descripcion = "",
                tiempo = "",
                errorMessage = null,
                successMessage = null
            )
        }
    }

    fun delete(tarea: tareaEntity) {
        viewModelScope.launch {
            repository.delete(tarea)
        }
    }

    fun isDescripcionDuplicado(descripcion: String): Boolean {
        val state = _uiState.value
        return if (state.tareaEditandaId != null) {

            state.tarea.any { it.descripcion == descripcion && it.tareaId != state.tareaEditandaId }
        } else {
            state.tarea.any { it.descripcion == descripcion }
        }
    }

    fun getTareaById(id: Int?): tareaEntity? {
        return _uiState.value.tarea.find { it.tareaId == id }
    }
}