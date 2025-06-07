package edu.ucne.ap2_p1_carloscustodio.data.sistema

import edu.ucne.ap2_p1_carloscustodio.data.local.entities.tareaEntity

data class tareaUiState (
    val tareaId: Int? = null,
    val descripcion: String = "",
    val tiempo: Int = 0,
    val errorMessage: String? = null,
    val tareaEditandaId: Int? = null,
    val successMessage: String? = null,
    val tarea: List<tareaEntity> = emptyList()
) {
    val editando: Boolean
        get() = tareaEditandaId != null
}
