package com.cvargas.myevents.data.api

import com.cvargas.myevents.data.model.CheckEvent
import com.cvargas.myevents.data.model.Event
import io.reactivex.Completable
import io.reactivex.Maybe
import io.reactivex.Single

class ApiHelper(private val apiService: ApiService) {
    fun listEvents(): Maybe<List<Event>> = apiService.listEvents()

    fun getEvent(id: String): Single<Event> = apiService.getEvent(id)

    fun checkIn(check: CheckEvent): Completable = apiService.checkIn(check)
}