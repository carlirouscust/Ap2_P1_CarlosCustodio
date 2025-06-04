package edu.ucne.ap2_p1_carloscustodio.data.di

import android.content.Context
import androidx.room.Room
import edu.ucne.ap2_p1_carloscustodio.data.local.database.tareaDb
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideTareaDb(
        @ApplicationContext appContext: Context
    ): tareaDb {
        return Room.databaseBuilder(
            appContext,
            tareaDb::class.java,
            "tareaDb"
        ).build()
    }

    @Provides
    fun provideTareaDao(db: tareaDb) = db.tareaDao()
}