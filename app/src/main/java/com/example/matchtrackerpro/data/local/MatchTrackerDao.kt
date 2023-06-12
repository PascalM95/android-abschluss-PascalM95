package com.example.matchtrackerpro.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.matchtrackerpro.data.datamodels.LeagueData
import com.example.matchtrackerpro.data.datamodels.TeamData

@Dao
interface MatchTrackerDao {

    //Funktion um eine Liste von LeagueData-Objekten in die Datenbank einzufügen
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(leagues: List<LeagueData>)

    //Funktion um eine Liste von TeamData-Objekten in die Datenbank einzufügen
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTeams(teams: List<TeamData>)

    //Funktion um eine Liste von LeagueData-Objekten in der Datenbank zu aktualisieren
    @Update
    suspend fun update(leagues: List<LeagueData>)

    //Funktion um Datensätze in der TAbelle 'team_table' zu löschen
    @Query("DELETE FROM team_table")
    suspend fun deleteTeams()

    // Funktion um alle Datensätze, bei denen die leagueId mit dem übergebenen Parameter übereinstimmt, abzurufen.
    @Query("SELECT * FROM matchTracker_database WHERE leagueId = :leagueId")
    suspend fun getLeagueById(leagueId: Int): LeagueData

    // Funktion um alle Datensätze der Tabelle 'matchTracker_database' abzurufen.
    @Query("SELECT * FROM matchTracker_database")
    fun getAllLeagues(): LiveData<List<LeagueData>>

    // Funktion um alle Datensätze, bei denen die leagueId mit dem übergebenen Parameter übereinstimmt, zu löschen.
    @Query("DELETE FROM matchTracker_database WHERE leagueId = :leagueId")
    suspend fun deleteById(leagueId: Int)

    //Funktion um alle Datensätze abzurufen, die zu einer bestimmten leagueId gehören.
    @Query("SELECT * FROM team_table WHERE leagueId = :leagueId")
    fun getTeamsById(leagueId: Int): List<TeamData>

    //Funktion um alle Datensätze abzurufen, die zu einer bestimmten teamId gehören.
    @Query("SELECT * FROM team_table WHERE teamId = :teamId")
    fun getTeamById(teamId: Int): TeamData
}