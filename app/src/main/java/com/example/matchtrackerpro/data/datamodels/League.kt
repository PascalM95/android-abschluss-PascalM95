package com.example.matchtrackerpro.data.datamodels


data class League (
    val leagueId: Long,
    val leagueName: String,
    val leagueImg: Int,
    val teams: List<Team>
        )