package com.example.matchtrackerpro.data.model

import java.io.StringBufferInputStream

data class League (
    val leagueId: Long,
    val leagueName: String,
    val teams: List<Team>
        )