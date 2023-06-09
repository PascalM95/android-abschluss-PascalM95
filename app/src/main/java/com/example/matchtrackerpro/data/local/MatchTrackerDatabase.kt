package com.example.matchtrackerpro.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.matchtrackerpro.data.datamodels.LeagueData
import com.example.matchtrackerpro.data.datamodels.TeamData

@Database(entities = [LeagueData::class, TeamData::class], version = 1)
abstract class MatchTrackerDatabase : RoomDatabase() {

    abstract val matchTrackerDao: MatchTrackerDao

    companion object {
        private lateinit var dbInstance: MatchTrackerDatabase

        fun getDatabase(context: Context): MatchTrackerDatabase {
            synchronized(MatchTrackerDatabase::class.java) {
                if(!::dbInstance.isInitialized) {
                    dbInstance = Room.databaseBuilder(
                        context.applicationContext,
                        MatchTrackerDatabase::class.java,
                        "matchTracker_database",
                    ).allowMainThreadQueries().build()
                }
            }
            return dbInstance
        }
    }
}