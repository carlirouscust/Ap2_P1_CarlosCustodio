package edu.ucne.ap2_p1_carloscustodio.data.sistema

import edu.ucne.ap2_p1_carloscustodio.data.local.entities.tareaEntity

sealed class TareaEvents {
    data class OnDescripcionChange(val descripcion: String) : TareaEvents()
    data class OnTiempoChange(val tiempo: Int) : TareaEvents()
    data class OnEditar(val tarea: tareaEntity) : TareaEvents()
    data class OnEliminar(val tarea: tareaEntity) : TareaEvents()
    object OnGuardar : TareaEvents()
    object OnCancelar : TareaEvents()
    object ClearMessages : TareaEvents()
}