package com.example.matchtrackerpro.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.matchtrackerpro.data.datamodels.Team

@Dao
interface MatchTrackerDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert (teams: List<Team>)

    @Update
    suspend fun update (teams: List<Team>)

    @Query ("SELECT * FROM Team")
    fun getAllTeams(): LiveData<List<Team>>

    @Query ("DELETE FROM Team WHERE teamId = :teamId")
    suspend fun deleteById (teamId: Int)
}