package com.example.matchtrackerpro.data.model


data class League (
    val leagueId: Long,
    val leagueName: String,
    val leagueImg: Int,
    val teams: List<Team>
        )