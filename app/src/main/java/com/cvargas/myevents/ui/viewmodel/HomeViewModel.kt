package com.cvargas.myevents.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.cvargas.myevents.data.api.ApiHelper
import com.cvargas.myevents.data.api.ApiServiceImpl
import com.cvargas.myevents.data.model.Event
import com.cvargas.myevents.data.repository.EventRepository
import com.cvargas.myevents.utils.Resource
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class HomeViewModel : ViewModel() {
    private val eventRepository: EventRepository = EventRepository(ApiHelper(ApiServiceImpl()))

    private val eventsLiveData = MutableLiveData<Resource<List<Event>>>()
    private val compositeDisposable = CompositeDisposable()

    init {
        fetchEvents()
    }

    private fun fetchEvents() {
        eventsLiveData.postValue(Resource.loading(null))
        compositeDisposable.add(
            eventRepository.listEvents()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ eventList ->
                    eventsLiveData.postValue(Resource.success(eventList))
                }, {
                    eventsLiveData.postValue(Resource.error("Something Went Wrong", null))
                })
        )
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }

    fun getEvents(): LiveData<Resource<List<Event>>> {
        return eventsLiveData
    }
}