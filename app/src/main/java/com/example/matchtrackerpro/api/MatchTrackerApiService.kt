package com.example.matchtrackerpro.api

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

val BASE_URL = TODO()

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface MatchTrackerApiService {
    //TODO
}

object MatchTrackerApi {
    val retrofitService: MatchTrackerApiService by lazy { retrofit.create(MatchTrackerApiService::class.java) }
}