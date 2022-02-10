package com.cvargas.myevents.data.repository

import com.cvargas.myevents.data.api.ApiHelper
import com.cvargas.myevents.data.model.CheckEvent
import com.cvargas.myevents.data.model.Event
import io.reactivex.Completable
import io.reactivex.Maybe
import io.reactivex.Single

class EventRepository(private val apiHelper: ApiHelper) {
    fun listEvents(): Maybe<List<Event>> = apiHelper.listEvents()

    fun getEvent(id: String): Single<Event> = apiHelper.getEvent(id)

    fun checkIn(check: CheckEvent): Completable = apiHelper.checkIn(check)
}