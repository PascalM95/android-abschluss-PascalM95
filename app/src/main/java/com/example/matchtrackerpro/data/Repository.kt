package com.example.matchtrackerpro.data

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.matchtrackerpro.data.datamodels.League
import com.example.matchtrackerpro.data.datamodels.LeagueData
import com.example.matchtrackerpro.data.datamodels.TeamData
import com.example.matchtrackerpro.data.local.MatchTrackerDatabase
import com.example.matchtrackerpro.data.remote.MatchTrackerApi
import kotlin.Exception

const val TAG = "Repository"

class Repository (private val api: MatchTrackerApi, private val database: MatchTrackerDatabase) {

    var leagues = MutableLiveData<List<League>>()

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

    fun getTeams(leagueId: Int): List<TeamData> {

        return database.matchTrackerDao.getTeamsById(leagueId)
    }

    fun getTeam(teamId: Int): TeamData {

        return database.matchTrackerDao.getTeamById(teamId)
    }

    suspend fun getLeagueById(leagueId: Int): LeagueData {
        return database.matchTrackerDao.getLeagueById(leagueId)
    }
}