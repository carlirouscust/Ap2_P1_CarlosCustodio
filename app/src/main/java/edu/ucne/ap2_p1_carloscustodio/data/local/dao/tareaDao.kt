package edu.ucne.ap2_p1_carloscustodio.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Update
import androidx.room.Upsert
import edu.ucne.ap2_p1_carloscustodio.data.local.entities.tareaEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface tareaDao {
    @Upsert
    suspend fun save(entidad: tareaEntity)

    @Query(
        """
            SELECT * FROM tarea
            WHERE tareaId = :id
            LIMIT 1
        """)
    suspend fun find(id: Int): tareaEntity?
    @Update
    suspend fun update(tarea: tareaEntity)
    @Delete
    suspend fun delete(tarea: tareaEntity)

    @Query("SELECT * FROM tarea")
    fun getAll(): Flow<List<tareaEntity>>

}