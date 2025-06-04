package edu.ucne.ap2_p1_carloscustodio.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import edu.ucne.ap2_p1_carloscustodio.data.local.entities.tareaEntity
import edu.ucne.ap2_p1_carloscustodio.data.local.dao.tareaDao

@Database(
    entities = [
        tareaEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class tareaDb : RoomDatabase() {
    abstract fun tareaDao(): tareaDao
}