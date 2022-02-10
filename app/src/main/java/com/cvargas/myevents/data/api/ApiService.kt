package com.cvargas.myevents.data.api

import com.cvargas.myevents.data.model.Event
import com.cvargas.myevents.data.model.CheckEvent
import io.reactivex.Completable
import io.reactivex.Maybe
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {
    @GET("/events")
    fun listEvents(): Maybe<List<Event>>

    @GET("/events/{id}")
    fun getEvent(@Path("id") id: String): Single<Event>

    @POST("/checkin")
    fun checkIn(@Body check: CheckEvent): Completable
}
