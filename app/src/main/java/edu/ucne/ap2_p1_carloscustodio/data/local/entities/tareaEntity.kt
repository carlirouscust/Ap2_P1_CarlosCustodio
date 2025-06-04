package edu.ucne.ap2_p1_carloscustodio.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Tarea")
data class tareaEntity (
    @PrimaryKey
    val tareaId: Int? = null,
    val descripcion: String = "",
    val tiempo: Int
)
