package com.example.matchtrackerpro.data

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.matchtrackerpro.data.datamodels.League
import com.example.matchtrackerpro.data.datamodels.LeagueData
import com.example.matchtrackerpro.data.datamodels.TeamData
import com.example.matchtrackerpro.data.local.MatchTrackerDatabase
import com.example.matchtrackerpro.data.remote.MatchTrackerApi
import kotlin.Exception

// Tag zum identifizieren der Log-Nachricht
const val TAG = "Repository"

class Repository (private val api: MatchTrackerApi, private val database: MatchTrackerDatabase) {

    //LiveData- Objekt, die eine Liste von League-Objekten enthält
    var leagues = MutableLiveData<List<League>>()

    //Die  "getLeagues" Funktion versucht, Ligen abzurufen und in eine LiveData-Variable mit dem Namen "leagues" zu setzen.
    //Anschließend werden die empfangenen Ligen in eine Liste von "LeagueData"-Objekten umgewandelt und in die Datenbank eingefügt.
    //Danach werden die Teams jeder Liga ebenfalls in eine Liste von "TeamData"-Objekten umgewandelt und in die Datenbank eingefügt.
    //Falls ein Fehler auftritt, wird eine entsprechende Fehlermeldung protokolliert.
    suspend fun getLeagues() {

        try {
            leagues.value = api.retrofitService.getLeagues().league

            val listOfLeagues = mutableListOf<LeagueData>()

            val listOfTeams = mutableListOf<TeamData>()

            for (league in leagues.value!!) {
                val currentLeague = LeagueData(league.leagueId, league.leagueName, league.leagueImg)

                listOfLeagues.add(currentLeague)
            }
            database.matchTrackerDao.insert(listOfLeagues.sortedBy { it.leagueId }.toList())

            for (league in leagues.value!!) {

                for (team in league.teams) {
                    val currentTeam = TeamData(0, league.leagueId, team.img, team.teamName, team.games, team.goals, team.points, team.founding, team.colors, team.stadium, team.seats)

                    listOfTeams.add(currentTeam)
                }
            }
            database.matchTrackerDao.deleteTeams()

            database.matchTrackerDao.insertTeams(listOfTeams.toList())

        } catch (e: Exception) {
            Log.e(TAG, "Leagues couldn't be loaded. \n${e.message}")
        }
    }

    //Funktion die, anhand des übergebenen Parameters leagueId, alle zugehörigen Teams aus der Datenbank abruft.
    fun getTeams(leagueId: Int): List<TeamData> {

        return database.matchTrackerDao.getTeamsById(leagueId)
    }

    //Funktion, die anhand des übergebenen Parameters teamId, das zugehörige Team aus der Datenbank abruft.
    fun getTeam(teamId: Int): TeamData {

        return database.matchTrackerDao.getTeamById(teamId)
    }

    //Funktion, die anhand des übergebenen Parameters leagueId, die zugehörige Liga aus der Datenbank abruft.
    suspend fun getLeagueById(leagueId: Int): LeagueData {
        return database.matchTrackerDao.getLeagueById(leagueId)
    }
}