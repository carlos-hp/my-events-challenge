package com.cvargas.myevents.data.api

import com.cvargas.myevents.data.model.CheckEvent
import com.cvargas.myevents.data.model.Event
import io.reactivex.Completable
import io.reactivex.Maybe
import io.reactivex.Single

class ApiServiceImpl : ApiService {
    override fun listEvents(): Maybe<List<Event>> = RetrofitClient.apiInterface.listEvents()

    override fun getEvent(id: String): Single<Event> = RetrofitClient.apiInterface.getEvent(id)

    override fun checkIn(check: CheckEvent): Completable = RetrofitClient.apiInterface.checkIn(check)
}