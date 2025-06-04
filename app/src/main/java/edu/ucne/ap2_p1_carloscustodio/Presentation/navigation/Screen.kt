package edu.ucne.ap2_p1_carloscustodio.Presentation.navigation

import edu.ucne.ap2_p1_carloscustodio.data.local.database.tareaDb
import kotlinx.serialization.Serializable

sealed class Screen {
    @Serializable
    data object List : Screen()
    @Serializable
    data class tarea(val tareaDb: Int?) : Screen()
}