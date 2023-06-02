package com.example.matchtrackerpro.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.matchtrackerpro.data.dataclasses.MatchTrackerData

@Database(entities = [MatchTrackerData::class], version = 1)
abstract class MatchTrackerDatabase : RoomDatabase() {

    abstract val matchTrackerDao: MatchTrackerDao
}

private lateinit var INSTANCE: MatchTrackerDatabase

fun getDatabase(context: Context): MatchTrackerDatabase {
    synchronized(MatchTrackerDatabase::class.java) {
        if (!::INSTANCE.isInitialized) {
            INSTANCE = Room.databaseBuilder(
                context.applicationContext,
                MatchTrackerDatabase::class.java,
                "matchTracker_database"
            )
                .build()
        }
    }
    return INSTANCE
}