package edu.ucne.ap2_p1_carloscustodio.data.local.repository


import edu.ucne.ap2_p1_carloscustodio.data.local.database.tareaDb
import edu.ucne.ap2_p1_carloscustodio.data.local.entities.tareaEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class tareaRepository @Inject constructor(
    private val tareaDb: tareaDb
) {
    suspend fun saveTarea(tarea: tareaEntity){
        tareaDb.tareaDao().save(tarea)
    }
    suspend fun delete(tarea: tareaEntity){
        return tareaDb.tareaDao().delete(tarea)
    }
    suspend fun updateTarea(tarea: tareaEntity){
        tareaDb.tareaDao().update(tarea)
    }
    suspend fun find(id: Int): tareaEntity? {
        return tareaDb.tareaDao().find(id)
    }
    fun getAll(): Flow<List<tareaEntity>>{
        return tareaDb.tareaDao().getAll()
    }

}