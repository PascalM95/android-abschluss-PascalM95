package com.example.matchtrackerpro.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.matchtrackerpro.data.datamodels.League
import com.example.matchtrackerpro.data.datamodels.Team

@Dao
interface MatchTrackerDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert (leagues: List<League>)

    @Update
    suspend fun update (leagues: List<League>)

    @Query("SELECT * FROM matchTracker_database WHERE leagueId = :leagueId")
    suspend fun getLeagueById(leagueId: Int): League

    @Query("SELECT * FROM matchTracker_database")
    fun getAllLeagues(): LiveData<List<League>>

    @Query("DELETE FROM matchTracker_database WHERE leagueId = :leagueId")
    suspend fun deleteById (leagueId: Int)
}