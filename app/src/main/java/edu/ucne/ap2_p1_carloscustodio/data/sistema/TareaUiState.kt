package edu.ucne.ap2_p1_carloscustodio.data.sistema

import edu.ucne.ap2_p1_carloscustodio.data.local.entities.tareaEntity

data class tareaUiState (
    val tareaid: Int? = null,
    val descripcion: String = "",
    val tiempo: String,
    val errorMessage: String? = null,
    val tareaEditandaId: Int? = null,
    val successMessage: String? = null,
    val tarea: List<tareaEntity> = emptyList()
)