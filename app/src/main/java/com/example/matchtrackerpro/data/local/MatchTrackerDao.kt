package com.example.matchtrackerpro.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.matchtrackerpro.data.datamodels.League
import com.example.matchtrackerpro.data.datamodels.LeagueData
import com.example.matchtrackerpro.data.datamodels.Team
import com.example.matchtrackerpro.data.datamodels.TeamData

@Dao
interface MatchTrackerDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert (leagues: List<LeagueData>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTeams (teams: List<TeamData>)

    @Update
    suspend fun update (leagues: List<LeagueData>)

    @Query("SELECT * FROM matchTracker_database WHERE leagueId = :leagueId")
    suspend fun getLeagueById(leagueId: Int): LeagueData

    @Query("SELECT * FROM matchTracker_database")
    fun getAllLeagues(): LiveData<List<LeagueData>>

    @Query("DELETE FROM matchTracker_database WHERE leagueId = :leagueId")
    suspend fun deleteById (leagueId: Int)

    @Query("SELECT * FROM team_table WHERE leagueId = :leagueId")
    fun getTeamsById(leagueId: Int): List<TeamData>

    @Query("SELECT * FROM team_table WHERE teamId = :teamId")
    fun getTeamById(teamId: Int): TeamData
}