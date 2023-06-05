package com.example.matchtrackerpro.data.datamodels


data class League (
    val leagueId: Int,
    val leagueName: String,
    val leagueImg: String,
    val teams: List<Team>
        )